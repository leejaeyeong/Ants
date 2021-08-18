package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.CommentRegisterPostReq;
import com.ssafy.api.response.BoardCommentRes;
import com.ssafy.api.response.BoardRes;
import com.ssafy.common.util.FileUtil;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardComment;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.entity.UserMarkerBoard;
import com.ssafy.db.repository.BoardCommentRepository;
import com.ssafy.db.repository.BoardRepository;
import com.ssafy.db.repository.BoardRepositorySupport;
import com.ssafy.db.repository.UserMarkerBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Autowired
    BoardCommentRepository boardCommentRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserMarkerBoardRepository userMarkerBoardRepository;

    @Autowired
    UserService userService;

    FileUtil fileUtil = FileUtil.getInstance();

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
    public List<BoardRes> getBoardLogList(LocalDate date, Long departmentId) {
//        List<Board> boards = boardRepository.findAllByTime(time);
//        for (Board board : boards) {
//            if (board.getWriter().getDepartment().getId() != departmentId) {
//                boards.remove(board);
//            }
//        }
//        if (boards == null) {
//            return null;
//        }
//        return convertToBoardRes(boards);
        return null;
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
                if (board.getBoardType().getId() == 2) {
                    commentResLists.add(new BoardCommentRes(comment.getComment(), comment.getRegistrationTime(), comment.getWriter().getUserId(), "https://us.123rf.com/450wm/thesomeday123/thesomeday1231712/thesomeday123171200009/91087331-%EB%82%A8%EC%84%B1%EC%9D%84%EC%9C%84%ED%95%9C-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%ED%94%84%EB%A1%9C%ED%95%84-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%B2%A1%ED%84%B0.jpg?ver=6", "익명"));
                } else {
                    commentResLists.add(new BoardCommentRes(comment.getComment(), comment.getRegistrationTime(), comment.getWriter().getUserId(), comment.getWriter().getProfileLocation(), comment.getWriter().getName()));
                }
            }
        }
        return BoardRes.of(board, isMarker, commentResLists);
    }

    @Override
    public boolean isMarker(Long boardId, String userId) {
        return boardRepositorySupport.isMarker(boardId, userId);
    }

    @Override
    public void increaseViewCnt(Long id) {
        boardRepositorySupport.increaseViewCnt(id);
    }

    @Override
    public BoardRes registerBoard(BoardRegisterPostReq boardRegisterPostReq) throws IOException {
        Board board = new Board();
        board.setTitle(boardRegisterPostReq.getTitle());
        board.setContent(boardRegisterPostReq.getContent());
        board.setWriter(userService.getUserByUserId(boardRegisterPostReq.getWriter()));
        board.setBoardType(boardRepositorySupport.findBoardTypeById(boardRegisterPostReq.getType()));
        board.setRegistrationTime(LocalDateTime.now());
        board.setView(0);

        File t = new File("..");
        String path = t.getCanonicalPath();
        fileUtil.createFilePath(path += "/media");
        fileUtil.createFilePath(path += "/board");

        String uuid = UUID.randomUUID().toString();
        File file = fileUtil.createFilePath(path + "/" + uuid);


//        String filePath = file.getAbsoluteFile() + "/" + boardRegisterPostReq.getImage().getOriginalFilename();
//        board.setImageLocation("/media/board/" + uuid + "/" + boardRegisterPostReq.getImage().getOriginalFilename());
//        File dest = new File(filePath);
//        boardRegisterPostReq.getImage().transferTo(dest);
        return BoardRes.of(boardRepository.save(board), false, new ArrayList<>());
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.delete(boardRepository.findBoardById(id));
    }

    @Override
    public BoardCommentRes registerComment(Long id, CommentRegisterPostReq commentRegisterPostReq) {
        BoardComment boardComment = new BoardComment();
        boardComment.setComment(commentRegisterPostReq.getComment());
        boardComment.setBoard(boardRepository.findBoardById(id));
        boardComment.setWriter(userService.getUserByUserId(commentRegisterPostReq.getWriter()));
        boardComment.setRegistrationTime(LocalDateTime.now());
        return BoardCommentRes.of(boardCommentRepository.save(boardComment));
    }

    public List<BoardRes> convertToBoardRes(List<Board> boards) {
        List<BoardRes> boardResList = new ArrayList<>();
        for (Board board : boards) {
            boardResList.add(new BoardRes(
                    board.getId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getRegistrationTime(),
                    board.getTime(),
                    board.getBoardType().getId(),
                    board.getWriter().getUserId(),
                    board.getView(),
                    board.getWriter().getProfileLocation(),
                    isMarker(board.getId(), board.getWriter().getUserId()),
                    board.getImageLocation(),
                    board.getWriter().getName()
            ));
        }
        return boardResList;
    }

    @Override
    public boolean boardMarkerToggle (Long boardId, String userId) {
        if (isMarker(boardId, userId)) {
            return removeMarker(boardId, userId);
        }
        return registerMarker(boardId, userId);
    }

    public boolean registerMarker(Long boardId, String userId) {
        UserMarkerBoard userMarkerBoard = new UserMarkerBoard();
        userMarkerBoard.setBoard(boardRepository.findBoardById(boardId));
        userMarkerBoard.setUser(userService.getUserByUserId(userId));
        userMarkerBoardRepository.save(userMarkerBoard);
        return true;
    }

    public boolean removeMarker(Long boardId, String userId) {
        userMarkerBoardRepository.delete(boardRepositorySupport.getUserMarker(boardId, userId));
        return false;
    }
}
