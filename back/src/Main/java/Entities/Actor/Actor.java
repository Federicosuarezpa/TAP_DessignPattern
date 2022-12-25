package Entities.Actor;

import Entities.Message.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Actor implements ActorInterface {
    private final Queue<Message> queue = new LinkedBlockingDeque<>();
    private String name;

    /**
     *
     * @param message
     */
    @Override
    public void addMessageQueue(Message message) { queue.add(message); }

    /**
     *
     * @param message
     */
    @Override
    public abstract void processMessage(Message message);

    /**
     *
     * @return Actor's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Actor's queue
     */
    public abstract Queue<Message> getQueueList();

    public Queue<Message> getQueue() { return queue; }
}
