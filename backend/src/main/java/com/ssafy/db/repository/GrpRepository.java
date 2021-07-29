package com.ssafy.db.repository;

import com.ssafy.db.entity.Grp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrpRepository extends JpaRepository<Grp, Long> {
    Optional<Grp> findByName(String name);
}
