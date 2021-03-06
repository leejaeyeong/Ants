package com.ssafy.db.repository;

import com.ssafy.db.entity.Dm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DmRepository extends JpaRepository<Dm, Long> {
}
