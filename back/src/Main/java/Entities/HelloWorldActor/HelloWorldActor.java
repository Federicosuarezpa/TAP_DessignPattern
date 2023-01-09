package Entities.HelloWorldActor;

import Entities.Actor.Actor;
import Entities.Enums.EventType;
import Entities.Message.Message;

import java.util.Queue;

public class HelloWorldActor extends Actor {

    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        String actorName = null;

        try {
            actorName = message.getFrom().getActor().getName();
        }catch (NullPointerException e) {

        }
        System.out.println("From: " + actorName + ": " + message.getBody());
    }

    /**
     *
     * @return Actor's queue
     */
    @Override
    public Queue<Message> getQueueList() { return this.getQueue(); }

}
