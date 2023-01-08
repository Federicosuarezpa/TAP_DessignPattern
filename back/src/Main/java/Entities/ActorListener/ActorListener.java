package Entities.ActorListener;

import Entities.Actor.Actor;
import Entities.Enums.EventType;
import Entities.Message.Message;

import javax.swing.plaf.SpinnerUI;

public interface ActorListener {
    public void update(EventType eventType, Actor actor, Message message);
    public void update(EventType eventType, Actor actor);
}
