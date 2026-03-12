package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Modules.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Modules_ {

	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleSortOrder
	 **/
	public static volatile SingularAttribute<Modules, Integer> moduleSortOrder;
	
	/**
	 * @see com.mc.icmc.domain.Modules#modulePublished
	 **/
	public static volatile SingularAttribute<Modules, Integer> modulePublished;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleCancel
	 **/
	public static volatile SingularAttribute<Modules, Integer> moduleCancel;
	
	/**
	 * @see com.mc.icmc.domain.Modules#roleModuleActions
	 **/
	public static volatile ListAttribute<Modules, RoleModuleAction> roleModuleActions;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleDateEdit
	 **/
	public static volatile SingularAttribute<Modules, LocalDate> moduleDateEdit;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleName
	 **/
	public static volatile SingularAttribute<Modules, String> moduleName;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleFocus
	 **/
	public static volatile SingularAttribute<Modules, Integer> moduleFocus;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleParentId
	 **/
	public static volatile SingularAttribute<Modules, Long> moduleParentId;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleId
	 **/
	public static volatile SingularAttribute<Modules, Long> moduleId;
	
	/**
	 * @see com.mc.icmc.domain.Modules
	 **/
	public static volatile EntityType<Modules> class_;
	
	/**
	 * @see com.mc.icmc.domain.Modules#moduleDateAdded
	 **/
	public static volatile SingularAttribute<Modules, LocalDate> moduleDateAdded;

	public static final String MODULE_SORT_ORDER = "moduleSortOrder";
	public static final String MODULE_PUBLISHED = "modulePublished";
	public static final String MODULE_CANCEL = "moduleCancel";
	public static final String ROLE_MODULE_ACTIONS = "roleModuleActions";
	public static final String MODULE_DATE_EDIT = "moduleDateEdit";
	public static final String MODULE_NAME = "moduleName";
	public static final String MODULE_FOCUS = "moduleFocus";
	public static final String MODULE_PARENT_ID = "moduleParentId";
	public static final String MODULE_ID = "moduleId";
	public static final String MODULE_DATE_ADDED = "moduleDateAdded";

}

