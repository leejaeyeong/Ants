package com.ssafy.api.service;

import com.ssafy.api.response.CalendarDetail;
import com.ssafy.db.entity.Department;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */

public interface CalendarService {
	List<CalendarDetail> getCalendarLogList(String searchDate, Long departmentId);
}
