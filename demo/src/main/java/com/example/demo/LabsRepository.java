package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface LabsRepository extends JpaRepository<Labs, Long> {
  
}