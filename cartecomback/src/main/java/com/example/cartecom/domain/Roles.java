package com.example.cartecom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @NotBlank
    @Size(max = 100)
    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    @Column(name = "role_sort_order")
    private int roleSortOrder;

    @Column(name = "role_cancel")
    private int roleCancel = 0;

    @Column(name = "role_date_added")
    private LocalDate roleDateAdded;

    @Column(name = "role_date_edit")
    private LocalDate roleDateEdit;

    @Column(name = "role_published")
    private int rolePublished;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleModuleAction> roleModuleActions = new ArrayList<>();

    @Column(name = "role_description")
    private String description;
}