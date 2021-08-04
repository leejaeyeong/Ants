package com.ssafy.api.service;

import com.ssafy.api.response.BoardCommentRes;
import com.ssafy.api.response.BoardRes;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardComment;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.repository.BoardRepository;
import com.ssafy.db.repository.BoardRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<BoardType> getBoardTypeList() {
        List<BoardType> boardTypes = null;
        if (boardRepositorySupport.getBoardTypeList().isPresent()) {
            boardTypes = boardRepositorySupport.getBoardTypeList().get();
        }
        return boardTypes;
    }

    public List<BoardRes> getBoardList() {
        List<BoardRes> boardResList = null;
        if (boardRepositorySupport.getBoardList().isPresent()) {
            List<Board> boards = boardRepositorySupport.getBoardList().get();
            boardResList = convertToBoardRes(boards);
        }
        return boardResList;
    }

    @Override
    public List<BoardRes> getBoardListByTypeId(Long id) {
        List<BoardRes> boardResList = null;
        if (boardRepositorySupport.getBoardListByTypeId(id).isPresent()) {
            List<Board> boards = boardRepositorySupport.getBoardListByTypeId(id).get();
            boardResList = convertToBoardRes(boards);
        }
        return boardResList;
    }

    @Override
    public List<BoardRes> getBoardListByMarker(String userId) {
        List<Long> userMarkerBoards = boardRepositorySupport.getUserMarkerList(userId);
        for (int i = 0; i <userMarkerBoards.size(); i++) {
            System.out.println(userMarkerBoards.get(i));
        }
        List<BoardRes> boardResList = new ArrayList<>();
        for (BoardRes boards : getBoardList()) {
            for (int i = 0; i < userMarkerBoards.size(); i++) {
                if (boards.getId() == userMarkerBoards.get(i)) {
                    boardResList.add(boards);
                    userMarkerBoards.remove(i--);
                }
            }
        }
        return boardResList;
    }

    @Override
    public List<BoardRes> getBoardByWriterId(String userId) {
        List<BoardRes> boardResList = null;
        if (boardRepositorySupport.getBoardByWriterId(userId).isPresent()) {
            List<Board> boards = boardRepositorySupport.getBoardByWriterId(userId).get();
            boardResList = convertToBoardRes(boards);
        }
        return boardResList;
    }

    @Override
    public List<BoardRes> getBoardByTitle(String title) {
        List<BoardRes> boardResList = null;
        List<Board> boards = boardRepository.findByTitleContains(title);
        if (boards != null) {
            boardResList = convertToBoardRes(boards);
        }
        return boardResList;
    }

    @Override
    public BoardRes getBoardDetail(Long id, String userId) {
        Board board = boardRepository.findBoardById(id);
        boolean isMarker = isMarker(id, userId);
        List<BoardCommentRes> commentResLists = null;
        if (boardRepositorySupport.getCommentByBoardId(id).isPresent()) {
            commentResLists = new ArrayList<>();
            List<BoardComment> comments = boardRepositorySupport.getCommentByBoardId(id).get();
            for (BoardComment comment : comments) {
                commentResLists.add(new BoardCommentRes(comment.getComment(), comment.getRegistrationTime(), comment.getWriter().getUserId(), comment.getWriter().getProfileLocation()));
            }
        }
        return BoardRes.of(board, isMarker, commentResLists);
    }

    @Override
    public boolean isMarker(Long boardId, String userId) {
        return boardRepositorySupport.isMarker(boardId, userId);
    }

    public List<BoardRes> convertToBoardRes(List<Board> boards) {
        List<BoardRes> boardResList = new ArrayList<>();
        for (Board board : boards) {
            boardResList.add(new BoardRes(
                    board.getId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getRegistrationTime(),
                    board.getBoardType().getId(),
                    board.getWriter().getUserId(),
                    board.getView(),
                    board.getWriter().getProfileLocation(),
                    false
            ));
        }
        return boardResList;
    }
}
