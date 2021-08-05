package com.ssafy.api.controller;

import com.ssafy.api.response.BoardRes;
import com.ssafy.api.service.BoardService;
import com.ssafy.api.service.DepartmentService;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.entity.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "부서 API", tags = {"Department"})
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping()
    @ApiOperation(value = "부서 목록", notes = "부서 목록을 반환한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "보드 타입 반환 성공")
    })
    public ResponseEntity<List<Department>> getDepartmentList() {
        return ResponseEntity.ok().body(departmentService.getDepartmentList());
    }
}
