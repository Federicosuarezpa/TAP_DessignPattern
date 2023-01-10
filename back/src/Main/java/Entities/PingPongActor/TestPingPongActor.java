package Entities.PingPongActor;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;
import Entities.MoninorService.MonitorService;

public class TestPingPongActor implements Runnable {
    private final static Integer NUMBERCOMMUNICATIONS = 100;
    private PingPongActor pingPongActor;

    private Thread actorThread;


    public TestPingPongActor(PingPongActor pingPongActor) {
        this.pingPongActor = pingPongActor;
    }

    public void pingPongActorStart() {
        PingPongActor pingPongActorFede = this.pingPongActor;
        PingPongActor pingPongActorNatalia = new PingPongActor();
        pingPongActorFede.getListeners().add(MonitorService.getInstance());
        pingPongActorNatalia.getListeners().add(MonitorService.getInstance());
        pingPongActorFede.setNumberTotalCommunications(NUMBERCOMMUNICATIONS);
        pingPongActorNatalia.setNumberTotalCommunications(NUMBERCOMMUNICATIONS);

        ActorProxy actorProxyFede = ActorContext.getInstance().spawnActor(this.pingPongActor.getName(), pingPongActorFede);
        ActorProxy actorProxyNatalia = ActorContext.getInstance().spawnActor("Natalia", pingPongActorNatalia);

        actorProxyFede.start();
        actorProxyNatalia.start();
        actorProxyFede.sendMessage(new Message(actorProxyNatalia, "Hola"));
    }

    public void run() {
        pingPongActorStart();
    }
    public void start() {
        actorThread = new Thread(this);
        actorThread.start();
    }

    public void stop() {
        actorThread.interrupt();
    }
}
