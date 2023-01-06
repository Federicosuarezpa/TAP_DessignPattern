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

import Entities.Actor.Actor;
import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.EncryptionDecorator.EncryptionDecorator;
import Entities.FirewallDecorator.FirewallDecorator;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.HttpRequestHandler.HttpRequestHandler;
import Entities.HttpServer.HttpServer;
import Entities.InsultActor.InsultActor;
import Entities.Message.Message;

import java.net.InetSocketAddress;

public class Main {
    private final static Integer port = 9000;
    public static void main(String[] args) throws Throwable {
//        HttpServer httpServer = new HttpServer(port);
//        httpServer.startServer();
        ActorProxy actor = ActorContext.getInstance().spawnActor("test", new EncryptionDecorator(new FirewallDecorator(new HelloWorldActor())));
        ActorProxy actor2 = ActorContext.getInstance().spawnActor("test", (new HelloWorldActor()));
        actor.sendMessage(new Message(actor2, "hola"));
        /*
        TODO
        - Testing unit (mirar video)
        - Observer (mirar video)
         */
    }
}
