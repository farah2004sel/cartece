package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Roles.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Roles_ {

	
	/**
	 * @see com.mc.icmc.domain.Roles#roleDateEdit
	 **/
	public static volatile SingularAttribute<Roles, LocalDate> roleDateEdit;
	
	/**
	 * @see com.mc.icmc.domain.Roles#roleModuleActions
	 **/
	public static volatile ListAttribute<Roles, RoleModuleAction> roleModuleActions;
	
	/**
	 * @see com.mc.icmc.domain.Roles#roleCancel
	 **/
	public static volatile SingularAttribute<Roles, Integer> roleCancel;
	
	/**
	 * @see com.mc.icmc.domain.Roles#roleSortOrder
	 **/
	public static volatile SingularAttribute<Roles, Integer> roleSortOrder;
	
	/**
	 * @see com.mc.icmc.domain.Roles#rolePublished
	 **/
	public static volatile SingularAttribute<Roles, Integer> rolePublished;
	
	/**
	 * @see com.mc.icmc.domain.Roles#roleId
	 **/
	public static volatile SingularAttribute<Roles, Long> roleId;
	
	/**
	 * @see com.mc.icmc.domain.Roles#roleName
	 **/
	public static volatile SingularAttribute<Roles, String> roleName;
	
	/**
	 * @see com.mc.icmc.domain.Roles
	 **/
	public static volatile EntityType<Roles> class_;
	
	/**
	 * @see com.mc.icmc.domain.Roles#parentId
	 **/
	public static volatile SingularAttribute<Roles, Long> parentId;
	
	/**
	 * @see com.mc.icmc.domain.Roles#roleDateAdded
	 **/
	public static volatile SingularAttribute<Roles, LocalDate> roleDateAdded;

	public static final String ROLE_DATE_EDIT = "roleDateEdit";
	public static final String ROLE_MODULE_ACTIONS = "roleModuleActions";
	public static final String ROLE_CANCEL = "roleCancel";
	public static final String ROLE_SORT_ORDER = "roleSortOrder";
	public static final String ROLE_PUBLISHED = "rolePublished";
	public static final String ROLE_ID = "roleId";
	public static final String ROLE_NAME = "roleName";
	public static final String PARENT_ID = "parentId";
	public static final String ROLE_DATE_ADDED = "roleDateAdded";

}

