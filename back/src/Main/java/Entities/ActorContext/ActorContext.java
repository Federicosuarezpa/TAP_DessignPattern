package Entities.ActorContext;

import Entities.Actor.Actor;
import Entities.ActorProxy.ActorProxy;
import Entities.Runner.Runner;

import java.util.HashMap;
import java.util.Map;

public class ActorContext {
    private final Map<String, ActorProxy> actors = new HashMap<>();


    private static final ActorContext actorContext = new ActorContext();

    private ActorContext() {}

    /**
     *
     * @return ActorContext singleton entity
     */
    public static ActorContext getInstance(){
        return actorContext;
    }

    /**
     *
     * @param name
     * @param actor
     * @return ActorProxy
     */
    public ActorProxy spawnActor(String name, Actor actor) {
        Runner runner = new Runner(actor);
        ActorProxy actorProxy = new ActorProxy(actor);
        actors.put(name, actorProxy);
        actor.setName(name);
        actor.setRunner(runner);
        actor.start();
        return actorProxy;
    }

    /**
     *
     * @param name
     * @return Actor if it's name exists
     */
    public ActorProxy lookup(String name) {
        return actors.get(name);
    }

    /**
     *
     * @return all actor's names
     */
    public String[] getNames() {
        return actors.keySet().toArray(new String[0]);
    }

}
