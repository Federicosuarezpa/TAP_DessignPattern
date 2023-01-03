package Entities.MoninorService;

import Entities.ActorContext.ActorContext;
import Entities.ActorListener.ActorListener;
import Entities.ActorProxy.ActorProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitorService {
    enum TrafficThreshold {
        LOW,
        MEDIUM,
        HIGH
    }

    enum Event {
        CREATED,
        STOPPED,
        ERROR
    }

    private final ActorContext actorContext = ActorContext.getInstance();
    private static final MonitorService monitorService = new MonitorService();
    private Map<TrafficThreshold, List<String>> traffic = new HashMap<>();
    private Map<Event, Integer> numberEvents = new HashMap<>();
    private Map<String, String[]> logsActorMessages = new HashMap<>();

    public MonitorService() {
    }

    public static MonitorService getInstance() {
        return monitorService;
    }

    public void monitorActor(String actorName, ActorListener listener) {
        ActorProxy users = actorContext.lookup(actorName);
        users.getActor().getListeners().add(listener);
    }

    public void unmonitor(String actorName, ActorListener listener) {
        ActorProxy users = actorContext.lookup(actorName);
        users.getActor().getListeners().remove(listener);
    }

    public void monitorAllActors(ActorListener listener) {
        String[] actorsNames = actorContext.getNames();
        for (String name : actorsNames) {
            ActorProxy users = actorContext.lookup(name);
            users.getActor().getListeners().add(listener);
        }
    }

    public Map<TrafficThreshold, List<String>> getTraffic() {
        return traffic;
    }

    public Integer getNumberMessagesActor(String actorName) {
        return 0;
    }

    public String[] getAllMessages(String[] actorsNames) {
        return new String[1];
    }


}
