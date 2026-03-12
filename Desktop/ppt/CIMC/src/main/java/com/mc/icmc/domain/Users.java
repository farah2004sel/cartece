package com.mc.icmc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 50)
    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String userName;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "user_email", nullable = false, unique = true, length = 100)
    private String userEmail;

    @NotBlank
    @Size(max = 200)
    @Column(name = "user_password", nullable = false, length = 200)
    private String userPassword;


    @Size(max = 100)
    @Column(name = "user_first_name")
    private String userFirstName;

    @Size(max = 100)
    @Column(name = "user_last_name")
    private String userLastName;


    @Size(max = 20)
    @Column(name = "user_phone")
    private String userPhone;


    @Size(max = 100)
    @Column(name = "user_key")
    private String userKey;

    @Size(max = 100)
    @Column(name = "user_code")
    private String userCode;

    @Column(name = "user_code_date_exp")
    private LocalDateTime userCodeDateExp;

    @Column(name = "user_confirmed_at")
    private LocalDateTime userConfirmedAt;

    @Column(name = "user_date_connect")
    private LocalDateTime userDateConnect;

    @Column(name = "user_failed_login_attempts")
    private int userFailedLoginAttempts = 0;

    @Column(name = "user_lock_until")
    private LocalDateTime userLockUntil;



    //  attributs system
    @Column(name = "user_date_added")
    private LocalDate userDateAdded;

    @Column(name = "user_date_edit")
    private LocalDate userDateEdit;

    @Column(name = "user_published")
    private int userPublished;

    @Column(name = "user_focus")
    private int userFocus;

    @Column(name = "user_cancel")
    private int userCancel = 0;

    @Column(name = "user_sort_order")
    private int userSortOrder;
    @Column(name = "user_parent_id")
    private Long userParentId;



    //  Relation : plusieurs users peuvent avoir le même rôle
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Roles role;



}
