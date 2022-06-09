package com.idmgmt.springboot.repository;

import com.idmgmt.springboot.model.IDM_Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDMMasterRepository extends JpaRepository <IDM_Master, String> {

}
