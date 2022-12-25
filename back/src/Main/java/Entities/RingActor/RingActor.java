package Entities.RingActor;

import Entities.Actor.Actor;
import Entities.Message.Message;

import java.util.Queue;

public class RingActor extends Actor {
    private Actor nextActor;

    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        nextActor.addMessageQueue(message);
    }

    /**
     *
     * @return Actor's queue
     */
    @Override
    public Queue<Message> getQueueList() {
        return this.getQueue();
    }
}
