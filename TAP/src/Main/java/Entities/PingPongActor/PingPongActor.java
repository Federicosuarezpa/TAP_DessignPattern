package Entities.PingPongActor;

import Entities.Actor.Actor;
import Entities.Message.Message;

import java.util.Queue;

public class PingPongActor extends Actor {
    Actor actorCommunicate;
    int numberOfCommunications = 0;

    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        if (numberOfCommunications > 100) {
            //stop
            return;
        }
        actorCommunicate.addMessageQueue(message);
        numberOfCommunications++;
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
