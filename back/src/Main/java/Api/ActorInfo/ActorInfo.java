package Api.ActorInfo;

public class ActorInfo {
    private String actorName;
    private Integer messagesQueue;
    private Integer messagesProcessed;
    private String status;

    public ActorInfo(String actorName, Integer messagesQueue, Integer messagesProcessed, String status) {
        this.actorName = actorName;
        this.messagesQueue = messagesQueue;
        this.messagesProcessed = messagesProcessed;
        this.status = status;
    }
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Integer getMessagesQueue() {
        return messagesQueue;
    }

    public void setMessagesQueue(Integer messagesQueue) {
        this.messagesQueue = messagesQueue;
    }

    public Integer getMessagesProcessed() {
        return messagesProcessed;
    }

    public void setMessagesProcessed(Integer messagesProcessed) {
        this.messagesProcessed = messagesProcessed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
