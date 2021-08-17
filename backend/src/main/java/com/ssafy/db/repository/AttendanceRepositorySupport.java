package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.QAttendance;
import com.ssafy.db.entity.Todo;
import com.ssafy.db.entity.User;
import org.apache.tomcat.jni.Local;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class AttendanceRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAttendance qAttendance = QAttendance.attendance;
    @Autowired
    AttendanceRepository attendanceRepository;
    public Attendance findAttendanceByUserId(String userId) {
        LocalDate now = LocalDate.now();
        Attendance attendance = jpaQueryFactory
                .select(qAttendance)
                .from(qAttendance)
                .where(qAttendance.user.userId.eq(userId).and(qAttendance.date.eq(now))).offset(0).limit(1).fetchFirst();

        if(attendance == null) return null;
        return attendance;
    }

    // 퇴근
    @Transactional
    public boolean checkOutUser(Attendance attendance) {
        LocalDate now = LocalDate.now();
        Long row = jpaQueryFactory.update(qAttendance)
                .set(qAttendance.checkOutTime, attendance.getCheckOutTime())
                .where(qAttendance.date.eq(now).and(qAttendance.user.userId.eq(attendance.getUser().getUserId())))
                .execute();
        if (row > 0)
            return true;
        return false;
    }

    // 근태 기록 조회 1개월
    public List<Attendance> findAllByDateBetween(Map<String, Object> dateMap) {
        int year = (Integer)dateMap.get("year");
        int month = (Integer)dateMap.get("month");
        User user = (User)dateMap.get("user");

        // 해당 월 전체 조회 구간
        LocalDate start = convertToLocalDate(year, month,1);
        LocalDate end = convertToLocalDate(year, month, start.with(lastDayOfMonth()).getDayOfMonth());

        List<Attendance> attendances = attendanceRepository.findAllByDateBetween(start, end).stream().filter(
                a -> a.getUser().equals(user)
        ).collect(Collectors.toList());

        if (attendances == null)
            return null;
        return attendances;
    }

    // 근태 시간 조회 1주일
    public Integer findAllByDateBetween(User user, String startDate, String endDate) {
        LocalDate start = convertToDate(startDate);
        LocalDate end = convertToDate(endDate);

        List<Attendance> attendances = attendanceRepository.findAllByDateBetween(start, end).stream().filter(
                a -> a.getUser().equals(user)
        ).collect(Collectors.toList());

        if (attendances == null) return null;

        int second = 0;
        for (Attendance attendance : attendances) {
            if (attendance.getCheckOutTime() == null) { // 퇴실 안한 경우 계산 안함
                continue;
            }
            Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
            second += duration.getSeconds();
        }
        return second / 3600;
    }

    public List<Attendance> getAttendanceByUserId(String userId) {
        List<Attendance> list = jpaQueryFactory.select(qAttendance).from(qAttendance)
                .where(qAttendance.user.userId.eq(userId)).fetch();
        if(list == null) return new ArrayList<>();
        return list;
    }

    public Optional<Attendance> getAttendanceToday(User user) {
        LocalDate now = LocalDate.now();
        Attendance attendance = jpaQueryFactory.select(qAttendance).from(qAttendance)
                .where(qAttendance.date.eq(now).and(qAttendance.user.userId.eq(user.getUserId()))).fetchOne();
        if (attendance == null) return Optional.empty();
        return Optional.ofNullable(attendance);
    }

    public LocalDate convertToLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    public LocalDate convertToDate(String date) {
        StringTokenizer st = new StringTokenizer(date, "-");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        return convertToLocalDate(year, month, day);
    }
}
