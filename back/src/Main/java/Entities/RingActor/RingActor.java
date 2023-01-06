package Entities.RingActor;

import Entities.Actor.Actor;
import Entities.Message.Message;

import java.util.Queue;

public class RingActor extends Actor {
    private Actor nextActor;
    private boolean flagMessageReceived = false;
    private Integer rounds = 0;
    private final static Integer TOTAL_ROUNDS = 100;


    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        rounds = rounds + 1;
        if (nextActor != null)
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

    public Actor getNextActor() {
        return nextActor;
    }

    public void setNextActor(Actor nextActor) {
        this.nextActor = nextActor;
    }

    public boolean isAlreadyRounds() {
        return rounds > TOTAL_ROUNDS;
    }
}
