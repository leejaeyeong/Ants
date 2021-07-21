package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QTeam;
import com.ssafy.db.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QTeam qTeam = QTeam.team;

    public Optional<List<Team>> findTeamByAdminId(String adminId) {
        List<Team> teams = jpaQueryFactory.select(qTeam).from(qTeam)
                        .where(qTeam.adminId.eq(adminId)).fetch();
        if(teams == null) return Optional.empty();
        return Optional.ofNullable(teams);
    }

    @Transactional
    public boolean updateTeam(Team team) {
        Long rows = jpaQueryFactory.update(qTeam)
                .set(qTeam.description, team.getDescription())
                .set(qTeam.adminId, team.getAdminId())
                .set(qTeam.name, team.getName())
                .where(qTeam.id.eq(team.getId()))
                .execute();
        if (rows > 0) return true;
        return false;
    }

    @Transactional
    public boolean deleteTeam(Long id){
        Long rows = jpaQueryFactory.delete(qTeam).where(qTeam.id.eq(id)).execute();
        if (rows > 0) return true;
        return false;
    }
}
