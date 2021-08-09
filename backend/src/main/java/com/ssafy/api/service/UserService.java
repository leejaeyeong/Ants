package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserTeamMappingPutReq;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.User;

import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo) throws Exception;
	User getUserByUserId(String userId);
	List<UserRes> findAll();
	boolean getTeamByUserId(String userId);
	boolean updateUserTeamInfo(UserTeamMappingPutReq userTeamMappingPutReq);
	boolean updateUser(User user);
	boolean deleteUser(String userId);
	boolean checkInUser(User user);
	boolean checkOutUser(User user);
	List<Attendance> findAllByDateBetween(Map<String, Object> dateMap);
	Integer findAllByDateBetween(User user, String startDate, String endDate);
	Department getDepartmentById(long departmentId);
	Attendance getAttendanceToday(User user);
}
