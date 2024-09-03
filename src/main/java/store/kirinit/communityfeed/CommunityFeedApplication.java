package store.kirinit.communityfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CommunityFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityFeedApplication.class, args);
    }

}
