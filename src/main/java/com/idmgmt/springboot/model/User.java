package com.idmgmt.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String firstName;

    @Column(name = "user_pw", nullable = false)
    private String password;

    @Column(name = "acc_non_locked")
    private boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "lock_time")
    private Date lockTime;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;

}