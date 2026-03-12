package com.mc.icmc.domain;

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
@Table(name = "actions")
public class Actions implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionId;


    @NotBlank
    @Size(max = 100)
    @Column(name = "action_title", nullable = false, length = 100)
    private String actionTitle;



    // Relation OneToMany avec RoleModuleAction
    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleModuleAction> roleModuleActions = new ArrayList<>();

    // attributs system
    @Column(name = "action_date_added")
    private LocalDate actionDateAdded;

    @Column(name = "action_date_edit")
    private LocalDate actionDateEdit;

    @Column(name = "action_published")
    private int actionPublished;

    @Column(name = "action_focus")
    private int actionFocus;

    @Column(name = "action_cancel")
    private int actionCancel = 0;

    @Column(name = "action_sort_order")
    private int actionSortOrder;
    @Column(name = "action_parent_id")
    private Long actionParentId;


}
