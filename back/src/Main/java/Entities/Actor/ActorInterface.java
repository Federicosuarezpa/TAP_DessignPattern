package Entities.Actor;

import Entities.ActorListener.ActorListener;
import Entities.Enums.EventType;
import Entities.Message.Message;

import java.util.Queue;

public interface ActorInterface {
    void addMessageQueue(Message message);
    void processMessage(Message message);
    Queue<Message> getQueueList();
    void notifyAllObservers(EventType eventType);
    void notifyAllObservers (EventType eventType , Message message);
}
