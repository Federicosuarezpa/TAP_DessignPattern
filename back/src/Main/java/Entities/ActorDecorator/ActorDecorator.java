package Entities.ActorDecorator;

import Entities.Actor.Actor;
import Entities.Actor.ActorInterface;
import Entities.Message.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class ActorDecorator extends Actor {
    private ActorInterface actor;
    private Queue<Message> queue;

    /**
     *
     * @param actor
     */
    public ActorDecorator(ActorInterface actor) {
        this.actor = actor;
        this.queue = actor.getQueueList();
    }

    /**
     *
     * @return ActorInterface
     */
    public ActorInterface getActor() {
        return actor;
    }

    /**
     *
     * @param actor
     */
    public void setActor(ActorInterface actor) {
        this.actor = actor;
    }
}
