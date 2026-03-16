package com.mc.icmc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log_user")
public class Log_User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_user_id")
    private Long logUserId;

    @Column(name = "log_user_uuid", unique = true, updatable = false, nullable = false)
    private UUID logUserUuid = UUID.randomUUID();

    //  Relation vers User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    //  Relation vers Module
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Modules module;

    //  Relation vers Action
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private Actions action;

    @NotBlank
    @Column(name = "log_user_title", nullable = false)
    private String logUserTitle;

    @Column(name = "log_user_date", nullable = false)
    private LocalDateTime logUserDate = LocalDateTime.now();

    @Column(name = "log_user_ip", length = 50)
    private String logUserIp;

    @Column(name = "mac_vendeur", length = 100)
    private String macVendeur;

    @Column(name = "log_user_agent", length = 255)
    private String logUserAgent;

    @Column(name = "log_user_agent_string", columnDefinition = "TEXT")
    private String logUserAgentString;

    @Column(name = "focus")
    private int focus;
}
