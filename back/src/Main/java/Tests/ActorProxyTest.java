package Tests;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.Insult.AddInsultMessage;
import Entities.Insult.GetInsultMessage;
import Entities.InsultActor.InsultActor;
import Entities.Message.Message;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ActorProxyTest {
    @Test
    public void testInstantiationActorProxy() {
        InsultActor insultActor = new InsultActor();
        ActorProxy actorProxy = new ActorProxy(insultActor);

        assertEquals(0, actorProxy.getQueueList().size());
        assertEquals(0, actorProxy.getActor().getListeners().size());
        assertEquals(insultActor, actorProxy.getActor());
        assertNotEquals(insultActor.getQueue(), actorProxy.getQueueList());
    }

    @Test
    public void testAddElementsQueue() {
        InsultActor insultActor = new InsultActor();
        ActorProxy actorProxy = new ActorProxy(insultActor);

        assertEquals(0, actorProxy.getQueueList().size());

        actorProxy.addMessageQueue(new Message(new ActorProxy(new HelloWorldActor()), "Hola"));

        assertEquals(1, actorProxy.getQueueList().size());
        assertEquals("Hola", Objects.requireNonNull(actorProxy.getQueueList().poll()).getBody());
    }

    @Test
    public void testReceiveMethod() throws InterruptedException {
        ActorContext actorContext = ActorContext.getInstance();
        ActorProxy actorProxy = actorContext.spawnActor("InsultActor", new InsultActor());
        actorProxy.sendMessage(new AddInsultMessage(actorProxy, "Test"));

        actorProxy.sendMessage(new GetInsultMessage(actorProxy));
        assertEquals("No hay insultos", actorProxy.receive().getBody());
        actorProxy.sendMessage(new GetInsultMessage(actorProxy));
        assertEquals("Test", actorProxy.receive().getBody());
        actorProxy.stop();
    }
}
