package Entities.HttpRequestHandler;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.HttpRequest.HttpRequest;
import Entities.HttpResponse.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpRequestHandler implements HttpHandler {
    ActorContext actorContext = ActorContext.getInstance();
    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpRequest httpRequest = objectMapper.readValue(parseRequest(exchange), HttpRequest.class);

        HttpResponse httpResponse = handleRequest(httpRequest);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        String jsonResponse = objectMapper.writeValueAsString(httpResponse);

        exchange.sendResponseHeaders(httpResponse.getStatusCode(), jsonResponse.length());

        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes());
        os.close();
    }

    private String readRequestBody(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private HttpResponse handleRequest(HttpRequest httpRequest) {
        String method = httpRequest.getMethod();
        System.out.println(method);
        String actorName = httpRequest.getNameActor();
        String message = httpRequest.getMessage();
        if (method == null) {
            return new HttpResponse(400, "Empty method, can't process the request.");
        }
        switch (method) {
            case "ListActors" -> {
                HttpResponse httpResponse = new HttpResponse(200);
                httpResponse.setActorNames(actorContext.getNames());
                return httpResponse;
            }
            case "CreateActor" -> {
                if (checkActorName(actorName)) {
                    return new HttpResponse(400, "Empty Actor's name, you need to specify the name of the actor to create it.");
                }
                ActorProxy actorProxy = actorContext.spawnActor(actorName, new HelloWorldActor());
                return new HttpResponse(200);
            }
            case "DeleteActor" -> {
                if (checkActorName(actorName)) {
                    return new HttpResponse(400, "Empty Actor's name, you need to specify the name of the actor to delete it.");
                }
                return new HttpResponse(200);
            }
            case "MonitorizeActors" -> {
                return new HttpResponse(200);
            }
            case "SendMessage" -> {
                if (checkActorName(actorName) && !checkMessageNotEmpty(message)) {
                    return new HttpResponse(400, "Empty actor or message field, you need to specify both.");
                }
                ActorProxy actorProxy = actorContext.lookup(actorName);
                return new HttpResponse(200);
            }
            default -> {
                return new HttpResponse(400, "Bad request, unknown method.");
            }
        }
    }

    private boolean checkActorName(String actorName) {
        return actorName == null;
    }

    private boolean checkMessageNotEmpty(String message) {
        return message != null;
    }

    private String parseRequest(HttpExchange exchange) throws IOException {
        InputStream reqBody = exchange.getRequestBody();

        return readRequestBody(reqBody);
    }
}
