package com.ssafy.db.repository;

import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.FileInfo;
import com.ssafy.db.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByDateBetween(LocalDate start, LocalDate end);
    List<Todo> findAllByDate(LocalDate date);
    List<Todo> findAllByUserId(String userId);

    @Override
    void deleteAll(Iterable<? extends Todo> entities);
    //    void deleteAllByUserId(String userId);
}
