package Entities.RingActor;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;
import Entities.MoninorService.MonitorService;

public class TestRingActor implements Runnable {
    private final static Integer ACTORS = 10;
    private Thread actorThread;
    private RingActor actor;

    public TestRingActor(RingActor ringActor) {
        this.actor = ringActor;
    }
    public void ringActorStart(RingActor firstRingActor) {
        RingActor[] ringActors = new RingActor[ACTORS];
        ringActors[0] = firstRingActor;
        for(int i = 1; i < ACTORS; i++) {
            ringActors[i] = new RingActor();
            ringActors[i].getListeners().add(MonitorService.getInstance());
            ringActors[i].setNextActor(ringActors[i - 1]);
        }
        ringActors[0].setNextActor(ringActors[ACTORS - 1]);
        ActorProxy[] actorProxies = new ActorProxy[ACTORS];
        int index = 0;
        for (RingActor ringActor: ringActors) {
            actorProxies[index] = ActorContext.getInstance().spawnActor(firstRingActor.getName() + - index, ringActor);
            index++;
        }
        actorProxies[0].sendMessage(new Message(actorProxies[0], "hola"));
        long start = System.currentTimeMillis();
        while (true) {
            if (checkFlagLastElement(ringActors[ACTORS - 1])) break;
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

    /**
     * Checkear como hacer tema de runner
     */
    @Override
    public void run() {
        this.ringActorStart(this.actor);
    }
    public void start() {
        actorThread = new Thread(this);
        actorThread.start();
    }

    public void stop() {
        actorThread.interrupt();
    }
}
