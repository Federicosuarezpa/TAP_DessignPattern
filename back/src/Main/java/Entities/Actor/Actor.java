package Entities.Actor;

import Entities.ActorListener.ActorListener;
import Entities.Message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Actor implements ActorInterface {
    private final Queue<Message> queue = new LinkedBlockingDeque<>();
    private String name;
    private List<ActorListener> listeners = new ArrayList<>();

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
    public abstract void processMessage(Message message);

    /**
     * @return Actor's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Actor's queue
     */
    public abstract Queue<Message> getQueueList();

    public Queue<Message> getQueue() {
        return queue;
    }

    public void attach(ActorListener observer) {
        listeners.add(observer);
    }

    public void detach(ActorListener observer) {
        listeners.add(observer);
    }

    public void notifyAllObservers(int state) {
        for (ActorListener observer : listeners) {
            observer.update(state);
        }
    }

    public List<ActorListener> getListeners() {
        return listeners;
    }
}
