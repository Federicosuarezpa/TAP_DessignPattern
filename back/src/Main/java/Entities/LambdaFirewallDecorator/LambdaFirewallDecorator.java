package Entities.LambdaFirewallDecorator;

import Entities.Actor.ActorInterface;
import Entities.ActorDecorator.ActorDecorator;
import Entities.Message.Message;

import java.util.Queue;

public class LambdaFirewallDecorator extends ActorDecorator {

    public LambdaFirewallDecorator(ActorInterface actor) {
        super(actor);
    }

    public void addClosureMessage(Message message) {

        this.getActor().addMessageQueue(message);
    }

    @Override
    public void processMessage(Message message) {

    }

    @Override
    public Queue<Message> getQueueList() {
        return null;
    }
}
