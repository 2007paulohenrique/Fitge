package com.fitge.api.repository.main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitge.api.model.main.MediaType;

@Repository
public interface MediaTypeRepository extends JpaRepository<MediaType, Byte> {
    Optional<MediaType> findByType(String type);
}
