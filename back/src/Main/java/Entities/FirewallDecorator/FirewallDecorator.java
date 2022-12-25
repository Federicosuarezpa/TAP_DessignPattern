package Entities.FirewallDecorator;

import Entities.Actor.ActorInterface;
import Entities.ActorContext.ActorContext;
import Entities.ActorDecorator.ActorDecorator;
import Entities.Message.Message;

import java.util.Queue;

public class FirewallDecorator extends ActorDecorator {
    public FirewallDecorator(ActorInterface actor) {
        super(actor);
    }

    /**
     *
     * @param message
     */
    public void processMessage(Message message) {
        ActorContext actorContext = ActorContext.getInstance();
        String actorName = null;
        try {
            actorName = message.getFrom().getActor().getName();
        }catch (NullPointerException e) {

        }
        if (actorContext.lookup(actorName) != null) {
            this.getActor().processMessage(message);;
        }
    }

    /**
     *
     * @param message
     */
    public void addMessageQueue(Message message) {
        this.getActor().addMessageQueue(message);
    }

    /**
     *
     * @return Actor's queue
     */
    public Queue<Message> getQueueList() { return this.getActor().getQueueList(); }

}
