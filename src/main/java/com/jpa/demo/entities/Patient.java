package com.jpa.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Patient implements Serializable{
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
private Long id;
@Column(name="NOM",length = 10)

@NotNull
@Size(min=5 ,max=15)
private String nom; 
@DateTimeFormat(pattern = "yyyy-MM-dd") 
@Temporal(TemporalType.DATE)
private Date dateNaissance;
@DecimalMin("10")
private int score;
private boolean malade;
}
 
