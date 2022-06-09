package com.idmgmt.springboot.service;

import com.idmgmt.springboot.model.IDM_Master;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDM_MasterService {
    List<IDM_Master> getAllIDMs();
    void saveIDM(IDM_Master idm);
    IDM_Master getIDMById(String id);
    void deleteIDMById(String id);
    Page<IDM_Master> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}