package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RoleModuleAction.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RoleModuleAction_ {

	
	/**
	 * @see com.mc.icmc.domain.RoleModuleAction#role
	 **/
	public static volatile SingularAttribute<RoleModuleAction, Roles> role;
	
	/**
	 * @see com.mc.icmc.domain.RoleModuleAction#module
	 **/
	public static volatile SingularAttribute<RoleModuleAction, Modules> module;
	
	/**
	 * @see com.mc.icmc.domain.RoleModuleAction#action
	 **/
	public static volatile SingularAttribute<RoleModuleAction, Actions> action;
	
	/**
	 * @see com.mc.icmc.domain.RoleModuleAction#id
	 **/
	public static volatile SingularAttribute<RoleModuleAction, Long> id;
	
	/**
	 * @see com.mc.icmc.domain.RoleModuleAction
	 **/
	public static volatile EntityType<RoleModuleAction> class_;

	public static final String ROLE = "role";
	public static final String MODULE = "module";
	public static final String ACTION = "action";
	public static final String ID = "id";

}

