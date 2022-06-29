package org.flexe.services;

import java.util.List;

import org.flexe.Produkt;
import org.flexe.dto.ProduktDTO;


public interface ProduktService {

	int saveProdukt(Produkt p);
	int deleteProdukt(int idProdukt);
	int updateProdukt(Produkt produkt);
List<Produkt> listProdukt();
public  ProduktDTO convertEntityToDto(Produkt produkt);
public  Produkt convertDtoToEntity(ProduktDTO produktDTO);
	public Produkt getOneProduktByIdProdukt(int idProdukt);
}
