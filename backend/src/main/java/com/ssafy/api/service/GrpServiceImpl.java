package com.ssafy.api.service;

import com.ssafy.api.request.GrpRegisterPostReq;
import com.ssafy.api.request.GrpUpdatePutReq;
import com.ssafy.db.entity.Grp;
import com.ssafy.db.repository.GrpRepository;
import com.ssafy.db.repository.GrpRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("groupService")
public class GrpServiceImpl implements GrpService {
    @Autowired
    GrpRepository grpRepository;

    @Autowired
    GrpRepositorySupport grpRepositorySupport;


    @Override
    public List<Grp> getAll() {
        return grpRepository.findAll();
    }

    @Override
    public List<Grp> getGroupByAdminId(String adminId) {
        return grpRepositorySupport.findTeamByAdminId(adminId).isPresent()? grpRepositorySupport.findTeamByAdminId(adminId).get():null;
    }

    @Override
    public Grp createGroup(GrpRegisterPostReq teamRegisterInfo) {
        Grp grp = new Grp();
        grp.setName(teamRegisterInfo.getName());
        grp.setDescription(teamRegisterInfo.getDescription());
        grp.setAdminId(teamRegisterInfo.getAdmin_id());
        return grpRepository.save(grp);
    }

    @Override
    public boolean updateGroup(GrpUpdatePutReq grpUpdatePutReq) {
        Grp grp = new Grp();
        grp.setId(grpUpdatePutReq.getId());
        grp.setDescription(grpUpdatePutReq.getDescription());
        grp.setName(grpUpdatePutReq.getName());
        grp.setAdminId(grpUpdatePutReq.getAdminId());
        return grpRepositorySupport.updateTeam(grp);
    }

    @Override
    public boolean deleteGroup(Long id) {
        return grpRepositorySupport.deleteTeam(id);
    }
}
