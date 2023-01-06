package com.sophos.laboratory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad del afiliado.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */

@Entity
@Table(name = "affiliate")
public class Affiliate {
	
	/**
	 * Id del afiliado.
	 */
	@Id
	@Column(name = "id_affiliate")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAffiliate;
	
	/**
	 * Nombre del afiliado.
	 */
	@Column(name = "name_affiliate")
	private String nameAffiliate;
	
	/**
	 * Edad del afiliado.
	 */
	@Column(name = "age_affiliate")
	private int ageAffiliate;
	
	/**
	 *Correo electrónico del afiliado. 
	 */
	@Column(name = "mail_affiliate")
	private String mailAffiliate;

	public Affiliate() {
		
	}

	public Affiliate(Long idAffiliate, String nameAffiliate, int ageAffiliate, String mailAffiliate) {
		this.idAffiliate = idAffiliate;
		this.nameAffiliate = nameAffiliate;
		this.ageAffiliate = ageAffiliate;
		this.mailAffiliate = mailAffiliate;
	}

	public Long getIdAffiliate() {
		return idAffiliate;
	}

	public void setIdAffiliate(Long idAffiliate) {
		this.idAffiliate = idAffiliate;
	}

	public String getNameAffiliate() {
		return nameAffiliate;
	}

	public void setNameAffiliate(String nameAffiliate) {
		this.nameAffiliate = nameAffiliate;
	}

	public int getAgeAffiliate() {
		return ageAffiliate;
	}

	public void setAgeAffiliate(int ageAffiliate) {
		this.ageAffiliate = ageAffiliate;
	}

	public String getMailAffiliate() {
		return mailAffiliate;
	}

	public void setMailAffiliate(String mailAffiliate) {
		this.mailAffiliate = mailAffiliate;
	}

	@Override
	public String toString() {
		return "Affiliate [idAffiliate=" + idAffiliate + ", nameAffiliate=" + nameAffiliate + ", ageAffiliate="
				+ ageAffiliate + ", mailAffiliate=" + mailAffiliate + "]";
	}
	
	
	
}
