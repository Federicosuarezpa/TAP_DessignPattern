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

import Entities.RingActor.RingActor;
import Entities.RingActor.TestRingActor;

public class Main {
    private final static Integer PORT = 9000;
    public static void main(String[] args) throws Throwable {
//        HttpServer httpServer = new HttpServer(port);
//        httpServer.startServer();
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
        - Ring actor / ping - pong
         */
        TestRingActor test = new TestRingActor();
    }
}
