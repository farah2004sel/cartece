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
@Table(name = "modules")
public class Modules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Long moduleId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "module_name", nullable = false, length = 100)
    private String moduleName;

    //  attributs system
    @Column(name = "module_sort_order")
    private int moduleSortOrder;

    @Column(name = "module_cancel")
    private int moduleCancel = 0;

    @Column(name = "module_date_added")
    private LocalDate moduleDateAdded;

    @Column(name = "module_date_edit")
    private LocalDate moduleDateEdit;

    @Column(name = "module_published")
    private int modulePublished;

    @Column(name = "module_focus")
    private int moduleFocus;

    @Column(name = "module_parent_id")
    private Long moduleParentId;


    // Relation OneToMany avec RoleModuleAction
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleModuleAction> roleModuleActions = new ArrayList<>();
}
