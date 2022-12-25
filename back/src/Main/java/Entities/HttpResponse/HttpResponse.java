package Entities.HttpResponse;

public class HttpResponse {
    private String[] actorNames;
    private String[] queue;
    private Integer[] messagesProcessed;
    private Integer statusCode;
    private String errorMessage = "";

    public HttpResponse(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public HttpResponse(Integer statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
    /**
     *
     * @return Names of all the actors active
     */
    public String[] getActorNames() {
        return actorNames;
    }

    /**
     *
     * @param actorNames
     */
    public void setActorNames(String[] actorNames) {
        this.actorNames = actorNames;
    }

    /**
     *
     * @return status of the messages processed for actors
     */
    public String[] getQueue() {
        return queue;
    }

    /**
     *
     * @param queue
     */
    public void setQueue(String[] queue) {
        this.queue = queue;
    }

    /**
     *
     * @return The number of messages processed by the actors
     */
    public Integer[] getMessagesProcessed() {
        return messagesProcessed;
    }

    /**
     *
     * @param messagesProcessed
     */
    public void setMessagesProcessed(Integer[] messagesProcessed) {
        this.messagesProcessed = messagesProcessed;
    }

    /**
     *
     * @return statusCode, 200 = OK, anything else = bad request / response
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *
     * @return Error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
