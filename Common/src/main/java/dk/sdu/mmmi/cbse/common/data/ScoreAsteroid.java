package dk.sdu.mmmi.cbse.common.data;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class ScoreAsteroid {

    private final RestTemplate restTemplate;
    private final String urlScore = "http://localhost:8080/score";

    public ScoreAsteroid() {
        this.restTemplate = new RestTemplate();
    }

    public int asteroidDestroyed(int points) {
        try {
            String url = urlScore + "?point=" + points;
            String scoreResponse = restTemplate.getForObject(url, String.class);
            System.out.println(scoreResponse);
            return Integer.valueOf(scoreResponse.trim());
        } catch (ResourceAccessException e) {
            System.out.println("No connection to score microservice");
            return 0;
        }
    }
}