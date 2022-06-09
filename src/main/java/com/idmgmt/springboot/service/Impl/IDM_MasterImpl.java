package com.idmgmt.springboot.service.Impl;

import com.idmgmt.springboot.model.IDM_Master;
import com.idmgmt.springboot.repository.IDMMasterRepository;
import com.idmgmt.springboot.service.IDM_MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IDM_MasterImpl implements IDM_MasterService {

    @Autowired
    private IDMMasterRepository idmRepository;

    @Override
    public List<IDM_Master> getAllIDMs() {
        return idmRepository.findAll();
    }

    @Override
    public void saveIDM(IDM_Master idm) {
        this.idmRepository.save(idm);
    }

    @Override
    public IDM_Master getIDMById(String application_id) {
        Optional<IDM_Master> optional = idmRepository.findById(application_id);
        IDM_Master idm;
        if (optional.isPresent()) {
            idm = optional.get();
        } else {
            throw new RuntimeException(" IDM not found for id :: " + application_id);
        }
        return idm;
    }

    @Override
    public void deleteIDMById(String id) {
        this.idmRepository.deleteById(id);
    }

    @Override
    public Page<IDM_Master> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.idmRepository.findAll(pageable);
    }
}