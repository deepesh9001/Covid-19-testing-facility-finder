package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabaseLabs {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabaseLabs.class);

  @Bean
  CommandLineRunner initDatabase1(LabsRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Labs("Lab Bikaner #1", "some road", 24.019084, 71.337677, 1)));
      log.info("Preloading " + repository.save(new Labs("Lab Hospital #2", "that road", 28.028836, 73.325136, 2)));
      log.info("Preloading " + repository.save(new Labs("Lab Bikaner #3", "some road", 28.15456, 73.132456, 3)));
      log.info("Preloading " + repository.save(new Labs("Lab Hospital #4", "that road", 29.028836, 74.325136, 4)));
      log.info("Preloading " + repository.save(new Labs("Lab Bikaner #5", "some road", 28.089084, 73.397677, 5)));
      log.info("Preloading " + repository.save(new Labs("Lab Hospital #6", "that road", 28.461236, 73.852147, 6)));
      log.info("Preloading " + repository.save(new Labs("Lab Bikaner #7", "some road", 28.256987, 73.101475, 7)));
      log.info("Preloading " + repository.save(new Labs("Lab Hospital #8", "that road", 28.028210, 73.325489, 8)));
      log.info("Preloading " + repository.save(new Labs("Lab Bikaner #9", "some road", 27.019084, 72.337677, 9)));
      log.info("Preloading " + repository.save(new Labs("Lab Hospital #10", "that road", 28.019850, 73.325896, 10)));
    };
  }
}