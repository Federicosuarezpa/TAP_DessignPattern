package Entities.Actor;

import Entities.Message.Message;

import java.util.Queue;

public interface ActorInterface {
    void addMessageQueue(Message message);
    void processMessage(Message message);
    Queue<Message> getQueueList();
}
