package Entities.PingPongActor;

import Entities.Actor.Actor;
import Entities.ActorContext.ActorContext;
import Entities.Enums.EventType;
import Entities.Message.Message;

import java.util.Queue;

public class PingPongActor extends Actor {
    private int numberOfCommunications = 0;
    private int numberTotalCommunications = 0;
    private static final int MILISECONDSTOSLEEP = 100;

    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        try {
            Thread.sleep(MILISECONDSTOSLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.messageProcessed();
        numberOfCommunications++;
        System.out.println("Message: " + message.getBody()
                + " From: " + message.getFrom().getActor().getName() +
                " Number: " + this.getNumberOfCommunications());
        message.getFrom().sendMessage(
                new Message(ActorContext.getInstance().lookup(this.getName()), message.getBody()));
        if (numberOfCommunications > numberTotalCommunications) {
            this.stop();
            message.getFrom().sendMessage(
                    new Message(ActorContext.getInstance().lookup(this.getName()), message.getBody()));
        }
    }

    public int getNumberTotalCommunications() {
        return numberTotalCommunications;
    }

    public void setNumberTotalCommunications(int numberTotalCommunications) {
        this.numberTotalCommunications = numberTotalCommunications;
    }

    public int getNumberOfCommunications() {
        return numberOfCommunications;
    }

    public void setNumberOfCommunications(int numberOfCommunications) {
        this.numberOfCommunications = numberOfCommunications;
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
