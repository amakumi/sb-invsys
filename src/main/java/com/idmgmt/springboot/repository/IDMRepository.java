package com.idmgmt.springboot.repository;

import com.idmgmt.springboot.model.IDM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDMRepository extends JpaRepository<IDM, String>{

}
