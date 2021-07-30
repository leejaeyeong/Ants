package com.ssafy.api.service;

import com.ssafy.api.response.BoardRes;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.repository.BoardRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Override
    public List<BoardType> getBoardTypeList() {
        List<BoardType> boardTypes = null;
        if (boardRepositorySupport.getBoardTypeList().isPresent()) {
            boardTypes = boardRepositorySupport.getBoardTypeList().get();
        }
        return boardTypes;
    }

    public List<BoardRes> getBoardList() {
        List<Board> boards = null;
        List<BoardRes> boardResList = null;
        if (boardRepositorySupport.getBoardList().isPresent()) {
            boards = boardRepositorySupport.getBoardList().get();
            boardResList = convertToBoardRes(boards);
        }
        return boardResList;
    }

    @Override
    public List<BoardRes> getBoardListByTypeId(Long id) {
        List<Board> boards = null;
        List<BoardRes> boardResList = null;
        if (boardRepositorySupport.getBoardListByTypeId(id).isPresent()) {
            boards = boardRepositorySupport.getBoardListByTypeId(id).get();
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
    public boolean isMarker(String userId, Long boardId) {
        return boardRepositorySupport.isMarker(userId, boardId);
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
                    board.getView()
            ));
        }
        return boardResList;
    }
}
