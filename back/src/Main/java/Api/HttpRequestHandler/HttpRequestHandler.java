package Api.HttpRequestHandler;

import Api.ActorInfo.ActorInfo;
import Entities.Actor.Actor;
import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.Enums.EventType;
import Entities.HelloWorldActor.HelloWorldActor;
import Api.HttpRequest.HttpRequest;
import Api.HttpResponse.HttpResponse;
import Entities.InsultActor.InsultActor;
import Entities.Message.Message;
import Entities.MoninorService.MonitorService;
import Entities.PingPongActor.PingPongActor;
import Entities.PingPongActor.TestPingPongActor;
import Entities.RingActor.RingActor;
import Entities.RingActor.TestRingActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

public class HttpRequestHandler implements HttpHandler {
    ActorContext actorContext = ActorContext.getInstance();
    MonitorService monitorService = MonitorService.getInstance();
    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpRequest httpRequest = objectMapper.readValue(parseRequest(exchange), HttpRequest.class);

        HttpResponse httpResponse = handleRequest(httpRequest);

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            exchange.sendResponseHeaders(204, -1);
        }

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
        return sb.toString();
    }

    private HttpResponse handleRequest(HttpRequest httpRequest) {
        String method = httpRequest.getMethod();
        String actorName = httpRequest.getNameActor();
        String message = httpRequest.getMessage();
        String actorType = httpRequest.getActorType();
        if (method == null) {
            return new HttpResponse(400, "Empty method, can't process the request.");
        }
        switch (method) {
            case "ListActors" -> {HttpResponse httpResponse = new HttpResponse(200);
                for (String actorsName: actorContext.getNames()) {
                    Integer numberOfMessagesPendent = monitorService.getNumberOfMessages().get(actorsName);
                    Integer messagesProcessed = monitorService.getNumberProcessedMessages().get(actorsName);
                    List<EventType> events = monitorService.getEvents().get(actorsName);
                    String status = "Running";
                    for (EventType event: events) {
                        if (event == EventType.ERROR || event == EventType.FINALIZATION) status = "Stopped";
                        if (event == EventType.CREATED) status = "Running";
                    }
                    ActorInfo actorInfo  = new ActorInfo(actorsName, numberOfMessagesPendent, messagesProcessed, status);
                    httpResponse.getActors().add(actorInfo);
                }
                return httpResponse;
            }
            case "CreateActor" -> {
                if (checkActorName(actorName)) {
                    return new HttpResponse(400, "Empty Actor's name, you need to specify the name of the actor to create it.");
                }
                if (actorType == null) {
                    return new HttpResponse(400, "Empty ActorsType name, you need to specify the name of the actor to create it.");
                }
                createActor(actorType, actorName);
                return new HttpResponse(200);
            }
            case "DeleteActor" -> {
                if (checkActorName(actorName)) {
                    return new HttpResponse(400, "Empty Actor's name, you need to specify the name of the actor to delete it.");
                }
                actorContext.lookup(actorName).stop();
                return new HttpResponse(200);
            }
            case "SendMessage" -> {
                if (checkActorName(actorName) && !checkMessageNotEmpty(message)) {
                    return new HttpResponse(400, "Empty actor or message field, you need to specify both.");
                }
                ActorProxy actorProxy = actorContext.lookup(actorName);
                actorProxy.sendMessage(new Message(actorProxy, message));
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

    private void createActor(String actorType, String actorName) {
        switch (actorType) {
            case "RingActor" -> {
                RingActor ringActor = new RingActor();
                ringActor.setName(actorName);
                ringActor.getListeners().add(monitorService);
                TestRingActor testRingActor = new TestRingActor(ringActor);
                testRingActor.start();
            }
            case "PingPongActor" -> {
                PingPongActor pingPongActor = new PingPongActor();
                pingPongActor.setName(actorName);
                TestPingPongActor testPingPongActor = new TestPingPongActor(pingPongActor);
                testPingPongActor.start();
            }
            default -> {
                Actor actor = actorInstance(actorType);
                actor.getListeners().add(monitorService);
                actorContext.spawnActor(actorName, actor);
            }
        }
    }

    private Actor actorInstance(String actorType) {
        switch (actorType) {
            case "HelloWorldActor" -> {
                return new HelloWorldActor();
            }
            case "InsultActor" -> {
                return new InsultActor();
            }
        }
        return new HelloWorldActor();
    }
}
