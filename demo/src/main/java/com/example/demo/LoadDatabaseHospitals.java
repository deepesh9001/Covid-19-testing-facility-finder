package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabaseHospitals {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabaseHospitals.class);

  @Bean
  CommandLineRunner initDatabase(HospitalsRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Hospitals("PBM Bikaner", "some road", 28.005300, 73.330685)));
      log.info("Preloading " + repository.save(new Hospitals("SPMC Hospital", "that road", 28.013780, 73.329699)));
      log.info("Preloading " + repository.save(new Hospitals("SPMC Hospital", "that road", 89.013780, 23.329699)));
      
    };
  }
}