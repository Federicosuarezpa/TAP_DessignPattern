package Entities.LambdaFirewallDecorator;

import Entities.Actor.ActorInterface;
import Entities.ActorDecorator.ActorDecorator;
import Entities.Message.Message;

import java.util.Queue;
import java.util.function.Predicate;

public class LambdaFirewallDecorator extends ActorDecorator {
    private Predicate<String> filter;

    public LambdaFirewallDecorator(ActorInterface actor) {
        super(actor);
    }

    public void addClosureMessage(Predicate<String> filter) {
        this.filter = filter;
    }

    @Override
    public void processMessage(Message message) {
        if (filter.test(message.getBody())) {
            this.getActor().processMessage(message);
        }
    }

    @Override
    public Queue<Message> getQueueList() {
        return null;
    }
}
