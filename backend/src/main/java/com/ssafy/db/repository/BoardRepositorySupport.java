package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class BoardRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QBoardType qBoardType = QBoardType.boardType;
    QBoard qBoard = QBoard.board;
    QUserMarkerBoard qUserMarkerBoard = QUserMarkerBoard.userMarkerBoard;
    QBoardComment qBoardComment = QBoardComment.boardComment;


    public Optional<List<BoardType>> getBoardTypeList() {
        List<BoardType> boardType =  jpaQueryFactory.select(qBoardType).from(qBoardType).fetch();
        if(boardType == null) return Optional.empty();
        return Optional.ofNullable(boardType);
    }

    public  Optional<List<Board>> getBoardList() {
        List<Board> board =  jpaQueryFactory.select(qBoard).from(qBoard).fetch();
        if(board == null) return Optional.empty();
        return Optional.ofNullable(board);
    }

    public Optional<List<Board>> getBoardListByTypeId(Long id) {
        List<Board> board =  jpaQueryFactory.select(qBoard).from(qBoard)
                            .where(qBoard.boardType.id.eq(id)).fetch();
        if(board == null) return Optional.empty();
        return Optional.ofNullable(board);
    }

    public Optional<List<BoardComment>> getCommentByBoardId(Long id) {
        List<BoardComment> boardComment =  jpaQueryFactory.select(qBoardComment).from(qBoardComment)
                .where(qBoardComment.board.id.eq(id)).fetch();
        if(boardComment == null) return Optional.empty();
        return Optional.ofNullable(boardComment);
    }

    public Optional<List<Board>> getBoardByWriterId(String userId) {
        List<Board> board  =  jpaQueryFactory.select(qBoard).from(qBoard)
                .where(qBoard.writer.userId.like("%" + userId + "%")).fetch();
        if(board == null) return Optional.empty();
        return Optional.ofNullable(board);
    }

    public BoardType getBoardType(Long id) {
        BoardType boardType = jpaQueryFactory
                .select(qBoardType)
                .from(qBoardType)
                .where(qBoardType.id.eq(id)).fetchOne();
        return boardType;
    }

    public List<Long> getUserMarkerList(String userId) {
        List<Long> list = jpaQueryFactory
                .select(qUserMarkerBoard.board.id)
                .from(qUserMarkerBoard)
                .where(qUserMarkerBoard.user.userId.eq(userId))
                .fetch();
        return list;
    }
    public boolean isMarker(String userId, Long boardId) {
        UserMarkerBoard userMarkerBoard = jpaQueryFactory
                .select(qUserMarkerBoard)
                .from(qUserMarkerBoard)
                .where(qUserMarkerBoard.user.userId.eq(userId).and(qUserMarkerBoard.board.id.eq(boardId)))
                .fetchOne();
        if (userMarkerBoard == null) return false;
        return true;
    }

}