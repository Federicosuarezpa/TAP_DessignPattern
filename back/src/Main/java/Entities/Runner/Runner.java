package Entities.Runner;

import Entities.Actor.Actor;
import Entities.Message.Message;
import sun.misc.SignalHandler;

public class Runner implements Runnable {
    private Actor actor;
    private Thread actorThread;

    public Runner(Actor actor) {
        this.actor = actor;
        this.start();
    }

    /**
     * Checkear como hacer tema de runner
     */
    @Override
    public void run() {
        while (!actorThread.isInterrupted()) {
            Message message = this.actor.getQueueList().poll();
            if (message != null) {
                this.actor.messageProcessed();
                this.actor.processMessage(message);
            }
        }
    }
    public void start() {
        actorThread = new Thread(this);
        actorThread.start();
    }

    public void stop() {
        actorThread.interrupt();
    }
}
