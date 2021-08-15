package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QoutLink;
import com.ssafy.db.entity.outLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LinkRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    QoutLink qoutLink = QoutLink.outLink;

    public Optional<List<outLink>> findLinksByUserId(String userId){
        List<outLink> list = jpaQueryFactory.select(qoutLink).from(qoutLink)
                .where(qoutLink.userId.eq(userId)).fetch();

        if(list == null) return Optional.empty();
        return Optional.of(list);
    }
}
