package com.ssafy.api.service;

import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.api.request.TeamUpdatePutReq;
import com.ssafy.db.entity.Team;
import com.ssafy.db.repository.TeamRepository;
import com.ssafy.db.repository.TeamRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamRepositorySupport teamRepositorySupport;


    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> getTeamByAdminID(String adminId) {
        return teamRepositorySupport.findTeamByAdminId(adminId).isPresent()?teamRepositorySupport.findTeamByAdminId(adminId).get():null;
    }

    @Override
    public Team createTeam(TeamRegisterPostReq teamRegisterInfo) {
        Team team = new Team();
        team.setName(teamRegisterInfo.getName());
        team.setDescription(teamRegisterInfo.getDescription());
        team.setAdminId(teamRegisterInfo.getAdmin_id());
        return teamRepository.save(team);
    }

    @Override
    public boolean updateTeam(TeamUpdatePutReq teamUpdatePutReq) {
        Team team = new Team();
        team.setId(teamUpdatePutReq.getId());
        team.setDescription(teamUpdatePutReq.getDescription());
        team.setName(teamUpdatePutReq.getName());
        team.setAdminId(teamUpdatePutReq.getAdminId());
        return teamRepositorySupport.updateTeam(team);
    }

    @Override
    public boolean deleteTeam(Long id) {
        return teamRepositorySupport.deleteTeam(id);
    }
}
