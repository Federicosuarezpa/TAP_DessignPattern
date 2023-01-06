package Tests;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.InsultActor.InsultActor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActorContextTest {
    @Test
    public void testInstantiationActorContext() {
        ActorContext actorContext = ActorContext.getInstance();

        assertEquals(0, actorContext.getNames().length);
    }

    @Test
    public void testAddActors() {
        ActorContext actorContext = ActorContext.getInstance();

        ActorProxy actorProxy = actorContext.spawnActor("Test", new InsultActor());
        assertEquals(actorProxy, actorContext.lookup("Test"));
        assertEquals(1, actorContext.getNames().length);
        actorProxy.stop();
    }
}
