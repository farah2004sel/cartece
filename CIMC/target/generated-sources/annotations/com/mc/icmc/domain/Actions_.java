package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Actions.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Actions_ {

	
	/**
	 * @see com.mc.icmc.domain.Actions#roleModuleActions
	 **/
	public static volatile ListAttribute<Actions, RoleModuleAction> roleModuleActions;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionDateEdit
	 **/
	public static volatile SingularAttribute<Actions, LocalDate> actionDateEdit;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionFocus
	 **/
	public static volatile SingularAttribute<Actions, Integer> actionFocus;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionTitle
	 **/
	public static volatile SingularAttribute<Actions, String> actionTitle;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionDateAdded
	 **/
	public static volatile SingularAttribute<Actions, LocalDate> actionDateAdded;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionCancel
	 **/
	public static volatile SingularAttribute<Actions, Integer> actionCancel;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionId
	 **/
	public static volatile SingularAttribute<Actions, Long> actionId;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionPublished
	 **/
	public static volatile SingularAttribute<Actions, Integer> actionPublished;
	
	/**
	 * @see com.mc.icmc.domain.Actions
	 **/
	public static volatile EntityType<Actions> class_;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionParentId
	 **/
	public static volatile SingularAttribute<Actions, Long> actionParentId;
	
	/**
	 * @see com.mc.icmc.domain.Actions#actionSortOrder
	 **/
	public static volatile SingularAttribute<Actions, Integer> actionSortOrder;

	public static final String ROLE_MODULE_ACTIONS = "roleModuleActions";
	public static final String ACTION_DATE_EDIT = "actionDateEdit";
	public static final String ACTION_FOCUS = "actionFocus";
	public static final String ACTION_TITLE = "actionTitle";
	public static final String ACTION_DATE_ADDED = "actionDateAdded";
	public static final String ACTION_CANCEL = "actionCancel";
	public static final String ACTION_ID = "actionId";
	public static final String ACTION_PUBLISHED = "actionPublished";
	public static final String ACTION_PARENT_ID = "actionParentId";
	public static final String ACTION_SORT_ORDER = "actionSortOrder";

}

