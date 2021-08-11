package com.ssafy.api.controller;

import com.ssafy.api.request.TodoRegisterReq;
import com.ssafy.api.response.TodoInfoRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.TodoService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Todo;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.DepartmentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "오늘의할일 API", tags = {"Todo"})
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private DepartmentRepository departmentRepository;

    // 할일 등록
    @PostMapping(value = "/register")
    @ApiOperation(value = "할일 등록")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Todo> registerTodo(@RequestBody TodoRegisterReq todoRegisterReq) {
        Todo todo = todoService.registerTodo(todoRegisterReq);
        return ResponseEntity.status(200).body(todo);
    }

    // 할일 목록 가져오기
    @GetMapping(value = "/")
    @ApiOperation(value = "할일 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<TodoInfoRes>> getTodoList(@RequestParam Long departmentId) {
        List<Todo> list = todoService.getTodoList(departmentId);
        List<TodoInfoRes> resList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            TodoInfoRes todoInfoRes = new TodoInfoRes();
            Todo todo = list.get(i);
            todoInfoRes.setDate(todo.getDate());
            todoInfoRes.setTime(todo.getTime());
            todoInfoRes.setTitle(todo.getTitle());
            todoInfoRes.setDepartmentName(departmentRepository.findDepartmentById(todo.getDepartment().getId()).get().getDepartmentName());

            resList.add(todoInfoRes);
        }
        return ResponseEntity.status(200).body(resList);
    }

    // 할일 목록 가져오기
    @GetMapping(value = "{year}/{month}/{day}")
    @ApiOperation(value = "일자별 할일 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<TodoInfoRes>> getTodoList(
            @PathVariable Integer year,
            @PathVariable Integer month,
            @PathVariable Integer day,
            @RequestParam Long departmentId) {

        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("year", year);
        dateMap.put("month", month);
        dateMap.put("day", day);
        dateMap.put("departmentId", departmentId);

        return ResponseEntity.status(200).body(todoService.findTodoByDate(dateMap));
    }
}
