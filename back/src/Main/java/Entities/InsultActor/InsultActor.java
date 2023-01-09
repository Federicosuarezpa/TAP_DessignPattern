package Entities.InsultActor;

import Entities.Actor.Actor;
import Entities.ActorProxy.ActorProxy;
import Entities.Enums.EventType;
import Entities.Insult.AddInsultMessage;
import Entities.Insult.GetAllInsultMessages;
import Entities.Insult.GetInsultMessage;
import Entities.Message.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class InsultActor extends Actor {
    private List<String> insults = new LinkedList<>();

    /**
     *
     * @param message
     */
    @Override
    public void processMessage(Message message) {
        this.messageProcessed();
        if (message instanceof GetInsultMessage) {
            if (insults.size() > 0) {
                Random r = new Random();
                ActorProxy actorProxy = message.getFrom();
                if (actorProxy.getActor() == this) {
                    actorProxy.addMessageQueue(new Message(actorProxy, insults.get(r.nextInt(insults.size()))));
                } else {
                    actorProxy.sendMessage(new Message(actorProxy, insults.get(r.nextInt(insults.size()))));
                }
            } else {
                ActorProxy actorProxy = message.getFrom();
                if (actorProxy.getActor() == this) {
                    actorProxy.addMessageQueue(new Message(actorProxy, "No hay insultos"));
                } else {
                    actorProxy.sendMessage(new Message(actorProxy, "No hay insultos"));
                }
            }
        } else if (message instanceof GetAllInsultMessages) {
            ActorProxy actorProxy = message.getFrom();
            if (actorProxy.getActor() == this) {
                actorProxy.addMessageQueue(new Message(actorProxy, insults.toString()));
            } else {
                actorProxy.sendMessage(new Message(actorProxy, insults.toString()));
            }
        } else if (message instanceof AddInsultMessage) {
            insults.add(message.getBody());
        }
    }

    /**
     *
     * @return Actor's queue
     */
    public Queue<Message> getQueueList() { return this.getQueue(); }

    public List<String> getInsults() {
        return insults;
    }

    public void setInsults(List<String> insults) {
        this.insults = insults;
    }
}
