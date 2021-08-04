package com.ssafy.api.service;

import com.ssafy.api.response.BoardRes;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.entity.Department;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */

public interface DepartmentService {
	List<Department> getDepartmentList();
}
