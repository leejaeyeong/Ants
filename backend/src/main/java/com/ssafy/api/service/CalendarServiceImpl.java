package com.ssafy.api.service;

import com.ssafy.api.response.*;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.FileInfo;
import com.ssafy.db.entity.Todo;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    TodoService todoService;

    @Autowired
    BoardService boardService;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Override
    public List<CalendarDetail> getCalendarLogList(String searchDate, Long departmentId) {
        LocalDate date = convertToDate(searchDate);
//        List<BoardRes> boardResList = boardService.getBoardLogList(date, departmentId);
//        List<TodoInfoRes> todos = TodoService

        // 파일 로그
        List<FileInfo> fileInfos = fileInfoRepository.findAllByDate(date);
        List<FileInfoRes> fileResult = new ArrayList<>();
        if (fileInfos != null) {
            for (FileInfo f : fileInfos) {
                if (f.getDepartment().getId() == departmentId) {
                    fileResult.add(FileInfoRes.of(f));
                }
            }
        }

        // 투두 로그
        List<Todo> todos = todoRepository.findAllByDate(date);
        List<TodoInfoRes> todoResult = new ArrayList<>();
        if (todos != null) {
            for (Todo todo : todos) {
                if (todo.getDepartment().getId() == departmentId) {
                    todoResult.add(TodoInfoRes.of(todo));
                }
            }
        }

        // 보드 로그
        List<Board> boards = boardRepository.findAllByDate(date);
        List<BoardLog> boardResult = new ArrayList<>();
        if (boards != null) {
            for (Board board : boards) {
                if (board.getWriter().getDepartment().getId() == departmentId) {
                    if (boardRepositorySupport.getCommentByBoardId(board.getId()).isPresent()) {
                        boardResult.add(BoardLog.of(board, boardRepositorySupport.getCommentByBoardId(board.getId()).get().size()));
                    } else {
                        boardResult.add(BoardLog.of(board, 0));
                    }
                }
            }
        }

        List<CalendarDetail> result = new ArrayList<>();
        result.addAll(fileResult);
        result.addAll(todoResult);
        result.addAll(boardResult);
        result.sort((o1, o2) -> {
            if (o1.getTime().isBefore(o2.getTime())) {
                return -1;
            } else {
                return 1;
            }
        });
        return result;
    }

    public LocalDate convertToDate(String date) {
        StringTokenizer st = new StringTokenizer(date, "-");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        return LocalDate.of(year, month, day);
    }
}
