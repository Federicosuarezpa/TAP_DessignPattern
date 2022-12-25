package Entities.HttpServer;

import Entities.HttpRequestHandler.HttpRequestHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

import static com.sun.net.httpserver.HttpServer.create;

public class HttpServer {
    private Integer port;
    public HttpServer (Integer port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        com.sun.net.httpserver.HttpServer server = create(new InetSocketAddress(port), 0);

        System.out.println("server started at " + port);

        server.createContext("/", new HttpRequestHandler());
        server.setExecutor(null);
        server.start();
    }
}
