package edu.tcu.cs.frogcrew;

import edu.tcu.cs.frogcrew.crewmember.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FrogcrewApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrogcrewApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

}
