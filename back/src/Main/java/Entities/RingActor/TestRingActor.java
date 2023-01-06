package Entities.RingActor;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;

public class TestRingActor {
    private final static Integer ACTORS = 100;
    public TestRingActor() throws InterruptedException {
        this.ringActorStart();
    }

    public void ringActorStart() throws InterruptedException {
        RingActor[] ringActors = new RingActor[ACTORS];
        for(int i = 0; i < ACTORS; i++) {
            ringActors[i] = new RingActor();
            if (i == ACTORS - 1) ringActors[i].setNextActor(ringActors[0]);
            else if (i > 0) ringActors[i].setNextActor(ringActors[i - 1]);
        }
        ringActors[0].setNextActor(ringActors[ACTORS - 1]);
        ActorProxy[] actorProxies = new ActorProxy[ACTORS];
        int index = 0;
        for (RingActor ringActor: ringActors) {
            actorProxies[index] = ActorContext.getInstance().spawnActor("Test", ringActor);
            index++;
        }
        // Get current time
        long start = System.currentTimeMillis();
        actorProxies[0].sendMessage(new Message(actorProxies[0], "hola"));
        while (true) {
            if (checkFlagLastElement(ringActors[ACTORS - 1])) break;
            Thread.sleep(1);
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println(elapsedTimeMillis);
        for (ActorProxy actorProxy: actorProxies) {
            actorProxy.stop();
        }
    }

    public boolean checkFlagLastElement(RingActor ringActor) {
        return ringActor.isAlreadyRounds();
    }
}
