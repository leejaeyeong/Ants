package com.ssafy.db.repository;

import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    @Override
    void deleteAll(Iterable<? extends BoardComment> entities);
    @Override
    void delete(BoardComment entity);
    List<BoardComment> findAllByWriterId(Long boardId);
}