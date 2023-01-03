package Entities.Runner;

import Entities.Actor.Actor;
import Entities.Message.Message;

public class Runner implements Runnable {
    private Actor actor;
    private Thread actorThread;

    Runnable shutdownHandler = () -> System.out.println("Shutting down thread..");

    public Runner(Actor actor) {
        this.actor = actor;
        this.start();
    }

    /**
     * Checkear como hacer tema de runner
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Message message = this.actor.getQueueList().poll();
            if (message != null)
                this.actor.processMessage(message);
        }
    }
    public void start() {
        actorThread = new Thread(shutdownHandler, "shutdownthread");
        Runtime.getRuntime().addShutdownHook(actorThread);
        actorThread.start();
    }

    public void stop() {
        actorThread.interrupt();
    }
}
