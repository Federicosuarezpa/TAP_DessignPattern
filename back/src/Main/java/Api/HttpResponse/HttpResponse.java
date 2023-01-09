package Api.HttpResponse;

import Api.ActorInfo.ActorInfo;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private ArrayList<ActorInfo> actors = new ArrayList<>();
    private Integer statusCode;
    private String errorMessage = "";

    public HttpResponse(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public HttpResponse(Integer statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }


    public ArrayList<ActorInfo> getActors() {
        return actors;
    }

    public void setActors(ArrayList<ActorInfo> actors) {
        this.actors = actors;
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
