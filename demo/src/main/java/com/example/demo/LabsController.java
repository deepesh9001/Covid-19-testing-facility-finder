package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LabsController {

  private final LabsRepository labrepository;

  LabsController(LabsRepository labrepository) {
    this.labrepository = labrepository;
  }

  // Aggregate root

  @GetMapping("/labs")
  List<Labs> all() {
    return labrepository.findAll();
  }

  @PostMapping("/labs")
  Labs newLabs(@RequestBody Labs newLabs) {
    return labrepository.save(newLabs);
  }

  // Single item

  @GetMapping("/labs/{id}")
  Labs one1(@PathVariable Long id) {

    return labrepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Could not find Lab " + id));
  }
}