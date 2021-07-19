package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.QConference;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class ConferenceRepositorySupport {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    QConference qConference = QConference.conference;
//    public Optional<List<Conference>> findConferenceByUserId(long ownerId) {
//        List<Conference> conferences = jpaQueryFactory.select(qConference).from(qConference)
//                                .where(qConference.user.id.eq(ownerId)).fetch();
//        if(conferences == null) return Optional.empty();
//        return Optional.ofNullable(conferences);
//    }
}
