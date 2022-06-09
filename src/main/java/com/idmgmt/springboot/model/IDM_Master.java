package com.idmgmt.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "idm_master")
public class IDM_Master {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "site")
    private String site;

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "insert_date")
    private String insDate;

    public String getINSERT_DATE() {
        insDate = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.ms.SSSS a ").format(new Timestamp(System.currentTimeMillis()));
        return insDate;
    }

    public String getORIGINAL_INSERT_DATE() {
        //insDate = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.ms.SSSS a ").format(new Timestamp(System.currentTimeMillis()));
        return insDate;
    }

    @Column(name = "update_date")
    private String updDate;

    public String getUPDATE_DATE() {
        updDate = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.ms.SSSS a ").format(new Timestamp(System.currentTimeMillis()));
        return updDate;
    }

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "port")
    private String port;

    @Column(name = "timeout")
    private int timeout;

    @Column(name = "read_timeout")
    private int r_timeout;



}