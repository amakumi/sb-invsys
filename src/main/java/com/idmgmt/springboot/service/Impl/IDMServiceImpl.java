package com.idmgmt.springboot.service.Impl;

import com.idmgmt.springboot.model.IDM;
import com.idmgmt.springboot.repository.IDMRepository;
import com.idmgmt.springboot.service.IDMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IDMServiceImpl implements IDMService {

    @Autowired
    private IDMRepository idmRepository;

    @Override
    public List<IDM> getAllIDMs() {
        return idmRepository.findAll();
    }

    @Override
    public void saveIDM(IDM idm) {
        this.idmRepository.save(idm);
    }

    @Override
    public IDM getIDMById(String application_id) {
        Optional<IDM> optional = idmRepository.findById(application_id);
        IDM idm = null;
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
    public Page<IDM> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.idmRepository.findAll(pageable);
    }
}
