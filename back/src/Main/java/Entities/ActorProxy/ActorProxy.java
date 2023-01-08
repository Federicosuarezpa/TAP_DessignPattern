package Entities.ActorProxy;

import Entities.Actor.Actor;
import Entities.Actor.ActorInterface;
import Entities.Enums.EventType;
import Entities.Message.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class ActorProxy implements ActorInterface {
    private Actor actor;
    private final Queue<Message> queue = new LinkedBlockingDeque<>();

    /**
     * @param actor
     */
    public ActorProxy(Actor actor) {
        this.actor = actor;
    }

    /**
     * @param message
     */
    public void sendMessage(Message message) {
        this.actor.addMessageQueue(message);
    }

    /**
     * @return
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * @return Message from the queue
     * @throws InterruptedException
     */
    public Message receive() throws InterruptedException {
        while (queue.size() == 0) {
            Thread.sleep(1000);
        }
        return queue.poll();
    }

    /**
     * @param message
     */
    @Override
    public void addMessageQueue(Message message) {
        queue.add(message);
    }

    /**
     * @param message
     */
    @Override
    public void processMessage(Message message) {
    }

    /**
     * @return ActorProxy's queue
     */
    @Override
    public Queue<Message> getQueueList() {
        return this.queue;
    }

    @Override
    public void notifyAllObservers(EventType eventType) { }
    public void notifyAllObservers(EventType eventType, Message message) { }

    public void start() {
        this.getActor().start();
    }

    public void stop() {
        this.getActor().stop();
    }
}
