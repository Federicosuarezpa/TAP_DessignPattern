package Entities.Insult;

import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;

public class GetInsultMessage extends Message {

    /**
     *
     * @param from
     */
    public GetInsultMessage(ActorProxy from) {
        super(from);
    }
}
