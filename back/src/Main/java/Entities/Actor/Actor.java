package Entities.Actor;

import Entities.ActorContext.ActorContext;
import Entities.ActorListener.ActorListener;
import Entities.Message.Message;
import Entities.Runner.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Actor implements ActorInterface {
    private final Queue<Message> queue = new LinkedBlockingDeque<>();
    private String name;
    private List<ActorListener> listeners = new ArrayList<>();
    private Runner runner;

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

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public void start() {
        this.runner.start();
    }

    public void stop() {
        this.runner.stop();
    }
}
