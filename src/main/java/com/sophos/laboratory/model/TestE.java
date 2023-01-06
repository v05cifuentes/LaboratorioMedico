package com.sophos.laboratory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad del examen o test.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */

@Entity
@Table (name = "test")
public class TestE {
	
	/**
	 * Id del test.
	 */
	@Id
	@Column(name = "id_test")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTest;
	
	/**
	 * Nombre del test o examen.
	 */
	@Column(name = "name_test")
	private String nameTest;
	
	/**
	 * Descripción general del test.
	 */
	@Column(name = "description_test")
	private String descriptionTest;

	public TestE() {
		
	}

	public TestE(Long idTest, String nameTest, String descriptionTest) {
		this.idTest = idTest;
		this.nameTest = nameTest;
		this.descriptionTest = descriptionTest;
	}

	public Long getIdTest() {
		return idTest;
	}

	public void setIdTest(Long idTest) {
		this.idTest = idTest;
	}

	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}

	public String getDescriptionTest() {
		return descriptionTest;
	}

	public void setDescriptionTest(String descriptionTest) {
		this.descriptionTest = descriptionTest;
	}

	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", nameTest=" + nameTest + ", descriptionTest=" + descriptionTest + "]";
	}
	
	
}
