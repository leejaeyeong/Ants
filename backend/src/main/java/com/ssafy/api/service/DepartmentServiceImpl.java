package com.ssafy.api.service;

import com.ssafy.api.response.BoardCommentRes;
import com.ssafy.api.response.BoardRes;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardComment;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.entity.Department;
import com.ssafy.db.repository.BoardRepository;
import com.ssafy.db.repository.BoardRepositorySupport;
import com.ssafy.db.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public List<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }
}
