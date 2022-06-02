package com.idmgmt.springboot.service;

import com.idmgmt.springboot.model.IDM;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDMService {
    List<IDM> getAllIDMs();
    void saveIDM(IDM idm);
    IDM getIDMById(String id);
    void deleteIDMById(String id);
    Page<IDM> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

