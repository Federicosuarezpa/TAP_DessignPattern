package Entities.ActorDecorator;

import Entities.Actor.Actor;
import Entities.Actor.ActorInterface;

public abstract class ActorDecorator extends Actor {
    private ActorInterface actor;

    /**
     *
     * @param actor
     */
    public ActorDecorator(ActorInterface actor) {
        this.actor = actor;
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
