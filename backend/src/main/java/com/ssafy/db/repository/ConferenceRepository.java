package com.ssafy.db.repository;

import com.ssafy.db.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {

//    Optional<List<Conference>> findByUserId(int ownerId);
//    Optional<List<Conference>> findByOwnerId(int ownerId);
}
