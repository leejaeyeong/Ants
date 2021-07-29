package com.ssafy.api.service;

import com.ssafy.api.request.GrpRegisterPostReq;
import com.ssafy.api.request.GrpUpdatePutReq;
import com.ssafy.db.entity.Grp;

import java.util.List;

public interface GrpService {
    List<Grp> getAll();
    List<Grp> getGroupByAdminId(String adminId);
    Grp createGroup(GrpRegisterPostReq teamRegisterInfo);
    boolean updateGroup(GrpUpdatePutReq grpUpdatePutReq);
    boolean deleteGroup(Long id);
}
