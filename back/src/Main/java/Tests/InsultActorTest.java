package Tests;

import Entities.ActorProxy.ActorProxy;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.Insult.GetInsultMessage;
import Entities.InsultActor.InsultActor;
import Entities.Message.Message;
import Entities.MoninorService.MonitorService;
import org.junit.Test;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class InsultActorTest<T> {
    @Test
    public void testInstantiationActor() {
        InsultActor insultActor = new InsultActor();

        assertEquals(0, insultActor.getQueueList().size());
        assertEquals(0, insultActor.getInsults().size());
        assertEquals(0, insultActor.getListeners().size());
    }
    @Test
    public void testAddElementsQueue() {
        InsultActor insultActor = new InsultActor();

        assertEquals(0, insultActor.getQueueList().size());

        insultActor.addMessageQueue(new Message(new ActorProxy(new HelloWorldActor()), "Hola"));

        assertEquals(1, insultActor.getQueueList().size());
        assertEquals("Hola", Objects.requireNonNull(insultActor.getQueue().poll()).getBody());
    }

    @Test
    public void testAddInsult() {
        InsultActor insultActor = new InsultActor();

        assertEquals(0, insultActor.getInsults().size());

        insultActor.getInsults().add("Bardzo");

        assertEquals(1, insultActor.getInsults().size());
        assertEquals("Bardzo", insultActor.getInsults().get(0));
    }
    @Test
    public void testAddListener() {
        InsultActor insultActor = new InsultActor();
        MonitorService actorLister = new MonitorService();

        assertEquals(0, insultActor.getListeners().size());

        insultActor.getListeners().add(actorLister);

        assertEquals(1, insultActor.getListeners().size());
        assertEquals(actorLister, insultActor.getListeners().get(0));
    }
    @Test
    public void testProcessMessage() {
        InsultActor insultActor = new InsultActor();
        InsultActor insultActor1 = new InsultActor();
        GetInsultMessage message = new GetInsultMessage(new ActorProxy(insultActor1));

        assertEquals(0, insultActor.getQueue().size());
        assertEquals(0, insultActor1.getQueue().size());

        insultActor.getInsults().add("Bardzo");
        insultActor.addMessageQueue(message);

        assertEquals(1, insultActor.getQueue().size());

        insultActor.processMessage(insultActor.getQueue().poll());

        assertEquals(0, insultActor.getQueue().size());
        assertEquals(1, insultActor1.getQueue().size());
    }
}
