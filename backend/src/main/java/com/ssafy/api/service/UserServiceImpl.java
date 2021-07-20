package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.repository.AttendanceRepository;
import com.ssafy.db.repository.AttendanceRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

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
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setDepartment(userRegisterInfo.getDepartment());
		user.setName(userRegisterInfo.getName());
		user.setPosition(userRegisterInfo.getPosition());
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
	public boolean updateUser(String userId, UserUpdatePutReq userUpdateInfo) {
		User user = getUserByUserId(userId);
		if (user == null) {
			return false;
		}
		user.setPosition(userUpdateInfo.getPosition());
		user.setDepartment(userUpdateInfo.getDepartment());
		user.setName(userUpdateInfo.getName());
		user.setPassword(passwordEncoder.encode(userUpdateInfo.getPassword()));
		return userRepositorySupport.updateUser(user);
	}

	@Override
	public boolean checkInUser(String userId) {
		if (getUserByUserId(userId) == null) {
			return false;
		}
		Attendance attendance = new Attendance();
		attendance.setUserId(userId);
		attendance.setCheckInTime(LocalTime.now());
		attendanceRepository.save(attendance);
		return true;
	}

	@Override
	public boolean checkOutUser(String userId) {
		if (getUserByUserId(userId) == null) {
			return false;
		}
		// 출석 정보 가져오기
		Attendance attendance = attendanceRepositorySupport.findAttendanceByUserId(userId);
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

	public boolean deleteUser(String userId) {
		return userRepositorySupport.deleteUserByUserId(userId);
	}
}
