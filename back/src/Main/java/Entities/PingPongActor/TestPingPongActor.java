package Entities.PingPongActor;

import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;

public class TestPingPongActor {
    private final static Integer NUMBERCOMMUNICATIONS = 100;

    public TestPingPongActor() {
        this.pingPongActorStart();
    }

    public void pingPongActorStart() {
        PingPongActor pingPongActorFede = new PingPongActor();
        PingPongActor pingPongActorNatalia = new PingPongActor();
        pingPongActorFede.setNumberTotalCommunications(NUMBERCOMMUNICATIONS);
        pingPongActorNatalia.setNumberTotalCommunications(NUMBERCOMMUNICATIONS);

        ActorProxy actorProxyFede = ActorContext.getInstance().spawnActor("Fede", pingPongActorFede);
        ActorProxy actorProxyNatalia = ActorContext.getInstance().spawnActor("Natalia", pingPongActorNatalia);

        actorProxyFede.start();
        actorProxyNatalia.start();

        actorProxyFede.sendMessage(new Message(actorProxyNatalia, "Hola"));
    }
}
