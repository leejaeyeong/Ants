package com.ssafy.db.repository;

import com.ssafy.db.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContains(String title);
    Board findBoardById(Long id);
    List<Board> findAllByDate(LocalDate date);
    @Override
    void deleteAll(Iterable<? extends Board> entities);
}