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

import Api.HttpServer.HttpServer;
import Entities.ActorContext.ActorContext;
import Entities.ActorListener.ActorListener;
import Entities.ActorProxy.ActorProxy;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.Message.Message;
import Entities.MoninorService.MonitorService;
import Entities.PingPongActor.TestPingPongActor;
import Entities.RingActor.RingActor;
import Entities.RingActor.TestRingActor;

public class Main {
    private final static Integer PORT = 9000;
    public static void main(String[] args) throws Throwable {
          HttpServer httpServer = new HttpServer(PORT);
          httpServer.startServer();
//        Actor actor = new HelloWorldActor();
//        actor = new FirewallDecorator(actor);
//        actor = new EncryptionDecorator(actor);
//        ActorProxy actor2 = ActorContext.getInstance().spawnActor("test", new HelloWorldActor());
//        actor.addMessageQueue(new Message(actor2, "asd"));
//        actor.addMessageQueue(new Message(actor2, "asd"));

//        ActorProxy actor = ActorContext.getInstance().spawnActor("test", new EncryptionDecorator(new FirewallDecorator(new HelloWorldActor())));
//        ActorProxy actor2 = ActorContext.getInstance().spawnActor("test", (new HelloWorldActor()));
//        actor.sendMessage(new Message(actor2, "hola"));
        /*
        TODO
        - Observer (mirar video)
         */
//            TestRingActor test = new TestRingActor();
        //TestPingPongActor test = new TestPingPongActor();

//        ActorProxy actorProxy = ActorContext.getInstance().spawnActor("Test", new HelloWorldActor());
//        ActorProxy actorProxy2 = ActorContext.getInstance().spawnActor("Test2", new HelloWorldActor());
//
//        MonitorService.getInstance().monitorActor("Test");
//        MonitorService.getInstance().monitorActor("Test2");
//        actorProxy.start();
//        actorProxy2.start();
//        actorProxy.sendMessage(new Message(actorProxy, "hola"));
//        actorProxy.sendMessage(new Message(actorProxy, "Test"));
//        actorProxy2.sendMessage(new Message(actorProxy, "Test"));
    }
}
