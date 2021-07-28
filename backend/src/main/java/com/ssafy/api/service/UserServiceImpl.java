package com.ssafy.api.service;

import com.ssafy.api.request.UserTeamMappingPutReq;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.Team;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRepositorySupport userRepositorySupport;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	AttendanceRepositorySupport attendanceRepositorySupport;

	@Autowired
	DepartmentRepositorySupport departmentRepositorySupport;

	@Autowired
	TeamRepositorySupport teamRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setName(userRegisterInfo.getName());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = null;
		if(userRepositorySupport.findUserByUserId(userId).isPresent())
			user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}

	@Override
	public boolean getTeamByUserId(String userId) {
		User user = null;
		if(userRepositorySupport.findUserByUserId(userId).isPresent())
			user = userRepositorySupport.findUserByUserId(userId).get();

		if(user == null || user.getTeam().getId() != 1) return false;
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepositorySupport.updateUser(user);
	}

	@Override
	public boolean updateUserTeamInfo(UserTeamMappingPutReq userTeamMappingPutReq){
		User user = getUserByUserId(userTeamMappingPutReq.getUserId());
		if(user == null) return false;

		Team team = teamRepositorySupport.findTeamById(userTeamMappingPutReq.getTeamId()).get();
		if(team == null) return false;

		user.setTeam(team);
		return userRepositorySupport.updateUser(user);
	}

	@Override
	public boolean checkInUser(User user) {
		if (attendanceRepositorySupport.getAttendanceToday(user).isPresent()) {
			return false;
		}
		Attendance attendance = new Attendance();
		attendance.setUser(user);
		attendance.setCheckInTime(LocalTime.now());
		attendanceRepository.save(attendance);
		return true;
	}

	@Override
	public boolean checkOutUser(User user) {
		// 출석 정보 가져오기
		Attendance attendance = attendanceRepositorySupport.findAttendanceByUserId(user.getUserId());
		if (attendance == null) {
			return  false;
		}
		attendance.setCheckOutTime(LocalTime.now());
		return attendanceRepositorySupport.checkOutUser(attendance);
	}

	@Override
	public List<Attendance> findAllByDateBetween(Map<String, Object> dateMap) {
		return attendanceRepositorySupport.findAllByDateBetween(dateMap);
	}

	@Override
	public Integer findAllByDateBetween(String userId, String startDate, String endDate) {

		return null;
	}

	public Attendance getAttendanceToday(User user) {
		Attendance attendance = null;
		if (attendanceRepositorySupport.getAttendanceToday(user).isPresent()) {
			attendance = attendanceRepositorySupport.getAttendanceToday(user).get();
		}
		return attendance;
	}

	@Override
	public Department getDepartmentById(long departmentId){
		Department department = null;
		if(departmentRepositorySupport.findDepartmentById(departmentId).isPresent()){
			department = departmentRepositorySupport.findDepartmentById(departmentId).get();
		} else department = new Department(1, "empty");
		return department;
	}

	@Override
	public boolean deleteUser(String userId) {
		return userRepositorySupport.deleteUserByUserId(userId);
	}
}
