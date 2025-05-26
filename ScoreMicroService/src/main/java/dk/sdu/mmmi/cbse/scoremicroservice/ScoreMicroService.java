package dk.sdu.mmmi.cbse.scoremicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoreMicroService {

    public static void main(String[] args) {
        SpringApplication.run(ScoreMicroService.class, args);
    }

    private int score = 0;

    @GetMapping("/score")
    public int calcScore(@RequestParam(value = "point") int point) {
        score += point;
        return score;
    }

}
