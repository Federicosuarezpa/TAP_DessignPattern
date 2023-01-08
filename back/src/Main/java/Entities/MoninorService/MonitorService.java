package Entities.MoninorService;

import Entities.Actor.Actor;
import Entities.ActorContext.ActorContext;
import Entities.ActorListener.ActorListener;
import Entities.ActorProxy.ActorProxy;
import Entities.Enums.EventType;
import Entities.Enums.TrafficLevel;
import Entities.Message.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitorService implements ActorListener{
    private final ActorContext actorContext = ActorContext.getInstance();
    private static final MonitorService monitorService = new MonitorService();
    private Map<TrafficLevel, List<String>> traffic = new HashMap<>();
    private Map<String, List<Message>> sentMessages = new HashMap<>();
    private Map<String, List<Message>> receivedMessages = new HashMap<>();
    private Map<String, List<EventType>> events = new HashMap<>();
    private Map<String, Integer> numberOfMessages = new HashMap<>();

    public MonitorService() {
        this.traffic.put(TrafficLevel.LOW, new ArrayList<>());
        this.traffic.put(TrafficLevel.MEDIUM, new ArrayList<>());
        this.traffic.put(TrafficLevel.HIGH, new ArrayList<>());
    }


    public static MonitorService getInstance() {
        return monitorService;
    }

    public void monitorActor(String actorName) {
        ActorProxy users = actorContext.lookup(actorName);
        users.getActor().getListeners().add(this);
    }

    public void unmonitorActor(String actorName) {
        ActorProxy users = actorContext.lookup(actorName);
        users.getActor().getListeners().remove(this);
    }

    public void monitorAllActors() {
        String[] actorsNames = actorContext.getNames();
        for (String name : actorsNames) {
            ActorProxy users = actorContext.lookup(name);
            users.getActor().getListeners().add(this);
        }
    }

    @Override
    public void update(EventType eventType, Actor actor, Message message) {
        this.updateEvent(eventType, actor.getName());
        this.updateMessagesReceived(actor.getName(), message);
        this.updateNumberMessages(eventType, actor.getName());
    }

    public void update(EventType eventType, Actor actor) {
        switch (eventType){
            case PROCESSEDMESSAGE -> updateNumberMessages(eventType, actor.getName());
            case FINALIZATION, ERROR -> emptyActorTraffic(actor.getName());
            case CREATED -> initializeListActor(actor.getName());
        }
        this.updateEvent(eventType, actor.getName());
    }

    public void updateEvent(EventType eventType, String actorName) {
        this.events.get(actorName).add(eventType);
    }

    public void updateNumberMessages(EventType eventType, String actorName) {
        int numberOfMessages = this.numberOfMessages.get(actorName);
        if (eventType == EventType.RECEIVEDMESSAGE) numberOfMessages++;
        if (eventType == EventType.PROCESSEDMESSAGE) numberOfMessages--;
        if (numberOfMessages <= 5) {
            if (!checkTrafficActor(TrafficLevel.LOW, actorName)) {
                this.traffic.get(TrafficLevel.LOW).add(actorName);
                if (checkTrafficActor(TrafficLevel.MEDIUM, actorName))
                    this.traffic.get(TrafficLevel.MEDIUM).remove(actorName);
            }
        } else if (numberOfMessages <= 10) {
            if (!checkTrafficActor(TrafficLevel.MEDIUM, actorName)) {
                this.traffic.get(TrafficLevel.MEDIUM).add(actorName);
                if (checkTrafficActor(TrafficLevel.LOW, actorName))
                    this.traffic.get(TrafficLevel.LOW).remove(actorName);
                if (checkTrafficActor(TrafficLevel.HIGH, actorName))
                    this.traffic.get(TrafficLevel.HIGH).remove(actorName);
            }
        } else {
            if (!checkTrafficActor(TrafficLevel.HIGH, actorName)) {
                this.traffic.get(TrafficLevel.HIGH).add(actorName);
                if (checkTrafficActor(TrafficLevel.MEDIUM, actorName))
                    this.traffic.get(TrafficLevel.MEDIUM).remove(actorName);
            }
        }
        this.numberOfMessages.put(actorName, numberOfMessages);
    }

    public void updateMessagesReceived(String actorName, Message message) {
        this.receivedMessages.get(actorName).add(message);
        this.sentMessages.get(message.getFrom().getActor().getName()).add(message);
    }

    public void initializeListActor(String actorName) {
        this.events.put(actorName, new ArrayList<>());
        this.sentMessages.put(actorName, new ArrayList<>());
        this.receivedMessages.put(actorName, new ArrayList<>());
        this.numberOfMessages.put(actorName, 0);
    }

    public boolean checkTrafficActor(TrafficLevel trafficLevel, String actorName) {
        return this.traffic.get(trafficLevel).contains(actorName);
    }

    public void emptyActorTraffic(String actorName) {
        this.numberOfMessages.put(actorName, 0);
        this.updateNumberMessages(EventType.FINALIZATION, actorName);
    }
}
