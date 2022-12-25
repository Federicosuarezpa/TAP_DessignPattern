package Entities.Message;


import Entities.ActorProxy.ActorProxy;

public class Message {
    private ActorProxy from;
    private String body;

    public Message(ActorProxy from, String body) {
        this.from = from;
        this.body = body;
    }

    /**
     *
     * @param from
     */
    public Message(ActorProxy from) {
        this.from = from;
    }

    /**
     *
     * @return ActorProxy that have sent the message
     */
    public ActorProxy getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(ActorProxy from) {
        this.from = from;
    }

    /**
     *
     * @return Message that we have sent
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }
}
