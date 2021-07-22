package com.ssafy.api.service;

import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.api.request.TeamUpdatePutReq;
import com.ssafy.db.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAll();
    List<Team> getTeamByAdminID(String adminId);
    Team createTeam(TeamRegisterPostReq teamRegisterInfo);
    boolean updateTeam(TeamUpdatePutReq teamUpdatePutReq);
    boolean deleteTeam(Long id);
}
