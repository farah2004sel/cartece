package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Nationalite.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Nationalite_ {

	
	/**
	 * @see com.mc.icmc.domain.Nationalite#code
	 **/
	public static volatile SingularAttribute<Nationalite, String> code;
	
	/**
	 * @see com.mc.icmc.domain.Nationalite#id
	 **/
	public static volatile SingularAttribute<Nationalite, Long> id;
	
	/**
	 * @see com.mc.icmc.domain.Nationalite
	 **/
	public static volatile EntityType<Nationalite> class_;
	
	/**
	 * @see com.mc.icmc.domain.Nationalite#nom
	 **/
	public static volatile SingularAttribute<Nationalite, String> nom;

	public static final String CODE = "code";
	public static final String ID = "id";
	public static final String NOM = "nom";

}

