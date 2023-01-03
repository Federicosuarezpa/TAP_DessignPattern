package Entities.Actor;

import Entities.ActorListener.ActorListener;
import Entities.Message.Message;

import java.util.Queue;

public interface ActorInterface {
    void addMessageQueue(Message message);
    void processMessage(Message message);
    Queue<Message> getQueueList();
    public void notifyAllObservers(int state);
}
