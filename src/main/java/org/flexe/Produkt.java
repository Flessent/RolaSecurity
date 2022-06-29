package org.flexe;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "Produkt")
@Entity(name = "Produkt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produkt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "id_produkt")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name="seq_produkt", sequenceName = "seq_produkt",allocationSize = 50, initialValue = 1)
	private int idProdukt;
	
	@NotNull(message = "Kann nicht null sein!")

	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@NotNull(message = "Kann nicht null sein!")

	@Basic(optional = false)
	@Column(name = "quantity")
	 @Size(min =1, max = 35, message="Die Länge ist nicht gültig !")

	private int  quantity;
	@NotNull(message = "Kann nicht null sein!")

	@Basic(optional = false)
	@Column(name = "price")
	private BigDecimal  price;
	
	@Basic(optional = false)
	 @Column(name = "verfuebarkeit")
	 private boolean verfuebarkeit;
	
	
	
	
	
	
}
