package com.idmgmt.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "idm_master")
public class IDM {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private String id;

    @Column(name = "function")
    private String function;

    @Column(name = "end_point")
    private String endpoint;

    @Column(name = "parameters")
    private String params;

    @Column(name = "insert_date")
    private String insDate;

    @Column(name = "update_date")
    private String updDate;

    @Column(name = "http_method")
    private String http;

}