package Entities.Insult;

import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;

public class AddInsultMessage extends Message {

    /**
     *
     * @param from
     * @param body
     */
    public AddInsultMessage(ActorProxy from, String body) {
        super(from, body);
    }
}
