/*import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.DynamicProxy.DynamicProxy;
import Entities.EncryptionDecorator.EncryptionDecorator;
import Entities.FirewallDecorator.FirewallDecorator;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.InsultActor.InsultActor;
import Entities.InsultService.InsultService;
import Entities.InsultService.InsultServiceInterface;
import Entities.Message.Message;*/

import Entities.ActorProxy.ActorProxy;
import Entities.HttpRequestHandler.HttpRequestHandler;
import Entities.HttpServer.HttpServer;
import Entities.InsultActor.InsultActor;

import java.net.InetSocketAddress;

public class Main {
    private final static Integer port = 9000;
    public static void main(String[] args) throws Throwable {
        HttpServer httpServer = new HttpServer(port);
        httpServer.startServer();
        /*
        TODO
        - Preguntar tema filtrar mensajes y hacerlo (rápido)
        - Tema runner (rápido)
        - Testing unit (mirar video)
        - Observer (mirar video)
        -
        -
         */
    }
}
