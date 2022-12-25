package Entities.InsultService;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class InsultService implements InsultServiceInterface{
    private List<String> insults = new LinkedList<>();

    /**
     *
     * @param message
     */
    public void addInsult(String message) {
        insults.add(message);
    }

    /**
     *
     * @return Return a random insult
     */
    public String getInsult() {
        Random r = new Random();
        return insults.get(r.nextInt(insults.size()));
    }

    /**
     *
     * @return all insults that we have stored in the queue
     */
    public String getAllInsults() {
        return insults.toString();
    }
}
