package com.enset.tpspringMVC.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data // generate getters and setters
@NoArgsConstructor // constructor with no params
@AllArgsConstructor // constructor with all params
@ToString

public class Patient {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@NotNull
	@Size(min = 5,max = 15)
	public String nom;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	public Date dateNaissance;
	@NotNull
	@DecimalMin("3")
	public int score;
	public boolean malade;
	public Patient(String nom, Date dateNaissance, int score, boolean malade) {
		super();
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.score = score;
		this.malade = malade;
	}
	
	
}
