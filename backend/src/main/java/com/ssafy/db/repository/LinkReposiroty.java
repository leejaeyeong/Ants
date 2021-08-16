package com.ssafy.db.repository;

import com.ssafy.db.entity.outLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkReposiroty extends JpaRepository<outLink, Long> {
    Optional<outLink> findById(Long id);
}
