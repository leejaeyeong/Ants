package com.ssafy.db.repository;

import com.ssafy.db.entity.DmRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DmRoomRepository extends JpaRepository<DmRoom, Long> {
}
