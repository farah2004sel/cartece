package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Commercant.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Commercant_ {

	
	/**
	 * @see com.mc.icmc.domain.Commercant#id
	 **/
	public static volatile SingularAttribute<Commercant, Long> id;
	
	/**
	 * @see com.mc.icmc.domain.Commercant#typePersonne
	 **/
	public static volatile SingularAttribute<Commercant, String> typePersonne;
	
	/**
	 * @see com.mc.icmc.domain.Commercant#nationaliteId
	 **/
	public static volatile SingularAttribute<Commercant, Long> nationaliteId;
	
	/**
	 * @see com.mc.icmc.domain.Commercant
	 **/
	public static volatile EntityType<Commercant> class_;
	
	/**
	 * @see com.mc.icmc.domain.Commercant#user
	 **/
	public static volatile SingularAttribute<Commercant, Users> user;
	
	/**
	 * @see com.mc.icmc.domain.Commercant#societe
	 **/
	public static volatile SingularAttribute<Commercant, String> societe;

	public static final String ID = "id";
	public static final String TYPE_PERSONNE = "typePersonne";
	public static final String NATIONALITE_ID = "nationaliteId";
	public static final String USER = "user";
	public static final String SOCIETE = "societe";

}

