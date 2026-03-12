package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;

@StaticMetamodel(Demande.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Demande_ {

	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeDateEdit
	 **/
	public static volatile SingularAttribute<Demande, LocalDate> demandeDateEdit;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandePublished
	 **/
	public static volatile SingularAttribute<Demande, Integer> demandePublished;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeId
	 **/
	public static volatile SingularAttribute<Demande, Long> demandeId;
	
	/**
	 * @see com.mc.icmc.domain.Demande#adresseEtablissementPrincipal
	 **/
	public static volatile SingularAttribute<Demande, String> adresseEtablissementPrincipal;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeDateAdded
	 **/
	public static volatile SingularAttribute<Demande, LocalDate> demandeDateAdded;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeSortOrder
	 **/
	public static volatile SingularAttribute<Demande, Integer> demandeSortOrder;
	
	/**
	 * @see com.mc.icmc.domain.Demande#typeDemande
	 **/
	public static volatile SingularAttribute<Demande, String> typeDemande;
	
	/**
	 * @see com.mc.icmc.domain.Demande#activiteSollicitee
	 **/
	public static volatile SingularAttribute<Demande, String> activiteSollicitee;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeFocus
	 **/
	public static volatile SingularAttribute<Demande, Integer> demandeFocus;
	
	/**
	 * @see com.mc.icmc.domain.Demande#commercant
	 **/
	public static volatile SingularAttribute<Demande, Commercant> commercant;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeParentId
	 **/
	public static volatile SingularAttribute<Demande, Long> demandeParentId;
	
	/**
	 * @see com.mc.icmc.domain.Demande
	 **/
	public static volatile EntityType<Demande> class_;
	
	/**
	 * @see com.mc.icmc.domain.Demande#dateDemande
	 **/
	public static volatile SingularAttribute<Demande, LocalDateTime> dateDemande;
	
	/**
	 * @see com.mc.icmc.domain.Demande#adresseSuccursales
	 **/
	public static volatile SingularAttribute<Demande, String> adresseSuccursales;
	
	/**
	 * @see com.mc.icmc.domain.Demande#demandeCancel
	 **/
	public static volatile SingularAttribute<Demande, Integer> demandeCancel;
	
	/**
	 * @see com.mc.icmc.domain.Demande#status
	 **/
	public static volatile SingularAttribute<Demande, String> status;

	public static final String DEMANDE_DATE_EDIT = "demandeDateEdit";
	public static final String DEMANDE_PUBLISHED = "demandePublished";
	public static final String DEMANDE_ID = "demandeId";
	public static final String ADRESSE_ETABLISSEMENT_PRINCIPAL = "adresseEtablissementPrincipal";
	public static final String DEMANDE_DATE_ADDED = "demandeDateAdded";
	public static final String DEMANDE_SORT_ORDER = "demandeSortOrder";
	public static final String TYPE_DEMANDE = "typeDemande";
	public static final String ACTIVITE_SOLLICITEE = "activiteSollicitee";
	public static final String DEMANDE_FOCUS = "demandeFocus";
	public static final String COMMERCANT = "commercant";
	public static final String DEMANDE_PARENT_ID = "demandeParentId";
	public static final String DATE_DEMANDE = "dateDemande";
	public static final String ADRESSE_SUCCURSALES = "adresseSuccursales";
	public static final String DEMANDE_CANCEL = "demandeCancel";
	public static final String STATUS = "status";

}

