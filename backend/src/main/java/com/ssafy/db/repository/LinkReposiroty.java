package com.ssafy.db.repository;

import com.ssafy.db.entity.outLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkReposiroty extends JpaRepository<outLink, Long> {
}
