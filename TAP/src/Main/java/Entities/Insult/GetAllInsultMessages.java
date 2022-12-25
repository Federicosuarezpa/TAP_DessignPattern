package Entities.Insult;

import Entities.ActorProxy.ActorProxy;
import Entities.Message.Message;

public class GetAllInsultMessages extends Message {

    /**
     *
     * @param from
     */
    public GetAllInsultMessages(ActorProxy from) {
        super(from);
    }
}
