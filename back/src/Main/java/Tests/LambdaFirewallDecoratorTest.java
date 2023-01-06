package Tests;

import Entities.ActorProxy.ActorProxy;
import Entities.EncryptionDecorator.EncryptionDecorator;
import Entities.FirewallDecorator.FirewallDecorator;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.LambdaFirewallDecorator.LambdaFirewallDecorator;
import Entities.Message.Message;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class LambdaFirewallDecoratorTest  {

    @Test
    public void testInstantiationActorDecorator() {
        EncryptionDecorator actor = new EncryptionDecorator(new FirewallDecorator(new HelloWorldActor()));

        assertEquals(0, actor.getQueueList().size());
        assertEquals(0, actor.getListeners().size());
    }

    @Test
    public void testLambdaFirewallDecortator() {
        LambdaFirewallDecorator lambdaFirewallDecorator = new LambdaFirewallDecorator(new HelloWorldActor());
        Predicate<String> containsA = x -> x.startsWith("A");
        lambdaFirewallDecorator.addClosureMessage(containsA);

        assertEquals(0, lambdaFirewallDecorator.getQueueList().size());
        ActorProxy actorProxy = new ActorProxy(lambdaFirewallDecorator);
        lambdaFirewallDecorator.addMessageQueue(new Message(actorProxy, "Test"));
        assertEquals(0, lambdaFirewallDecorator.getQueueList().size());
        lambdaFirewallDecorator.addMessageQueue(new Message(actorProxy, "Atest"));
        assertEquals(1, lambdaFirewallDecorator.getQueueList().size());
    }

}
