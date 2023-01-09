package Entities.EncryptionDecorator;

import Entities.Actor.ActorInterface;
import Entities.ActorDecorator.ActorDecorator;
import Entities.Enums.EventType;
import Entities.Message.Message;

import java.util.Queue;

public class EncryptionDecorator extends ActorDecorator {

    public EncryptionDecorator(ActorInterface actor) {
        super(actor);
    }

    /**
     *
     * @param message
     */
    public void addMessageQueue(Message message) {
        this.messageProcessed();
        this.getActor().addMessageQueue(message);
    }

    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        this.getActor().processMessage(message);
    }

    /**
     *
     * @return Actor's queue
     */
    public Queue<Message> getQueueList() { return this.getActor().getQueueList(); }

}
