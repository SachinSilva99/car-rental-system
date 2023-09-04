package com.carentalsystem.repo;

import com.carentalsystem.entity.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRequestRepo extends JpaRepository<RentalRequest, String> {
}
