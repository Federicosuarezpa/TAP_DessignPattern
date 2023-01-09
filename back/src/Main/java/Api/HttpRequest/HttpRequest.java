package Api.HttpRequest;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HttpRequest {
    private String nameActor = null;
    private String message = null;
    private String method = null;
    private String actorType = null;

    /**
     *
     * @return Name of the actor
     */
    public String getNameActor() {
        return nameActor;
    }

    /**
     *
     * @param nameActor
     */
    public void setNameActor(String nameActor) {
        this.nameActor = nameActor;
    }

    /**
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return Method to execute
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public String getActorType() {
        return actorType;
    }

    public void setActorType(String actorType) {
        this.actorType = actorType;
    }
}
