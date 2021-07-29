package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Grp;
import com.ssafy.db.entity.QGrp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class GrpRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QGrp qGrp = QGrp.grp;

    public Optional<List<Grp>> findTeamByAdminId(String adminId) {
        List<Grp> grps = jpaQueryFactory.select(qGrp).from(qGrp)
                        .where(qGrp.adminId.eq(adminId)).fetch();
        if(grps == null) return Optional.empty();
        return Optional.ofNullable(grps);
    }

    @Transactional
    public boolean updateTeam(Grp grp) {
        Long rows = jpaQueryFactory.update(qGrp)
                .set(qGrp.description, grp.getDescription())
                .set(qGrp.adminId, grp.getAdminId())
                .set(qGrp.name, grp.getName())
                .where(qGrp.id.eq(grp.getId()))
                .execute();
        if (rows > 0) return true;
        return false;
    }

    @Transactional
    public boolean deleteTeam(Long id){
        Long rows = jpaQueryFactory.delete(qGrp).where(qGrp.id.eq(id)).execute();
        if (rows > 0) return true;
        return false;
    }

    public Optional<Grp> findTeamById(Long id) {
        Grp grp = jpaQueryFactory.select(qGrp).from(qGrp)
                .where(qGrp.id.eq(id)).fetchOne();
        if(grp == null) return Optional.empty();
        return Optional.ofNullable(grp);
    }
}
