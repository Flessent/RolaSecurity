package org.flexe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduktDTO {
	private int idProdukt;
private String name;
private float price;
private int quantity;
private boolean verfuebarkeit;

	
	
}
