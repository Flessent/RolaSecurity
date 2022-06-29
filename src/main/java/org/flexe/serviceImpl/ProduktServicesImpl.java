package org.flexe.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.flexe.Produkt;
import org.flexe.dto.ProduktDTO;
import org.flexe.repository.ProduktRepository;
import org.flexe.services.ProduktService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ProduktServicesImpl  implements ProduktService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProduktRepository produktRepository;

	public ProduktDTO convertEntityToDto(Produkt produkt){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    ProduktDTO produktDTO = new ProduktDTO();
	    produktDTO = modelMapper.map(produkt, ProduktDTO.class);
	    produktDTO.setIdProdukt(produkt.getIdProdukt());

	    return produktDTO;
	}

	public Produkt convertDtoToEntity(ProduktDTO produktDTO){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    Produkt produkt = new Produkt();
	    produkt = modelMapper.map(produktDTO, Produkt.class);

	    return produkt;
	} 
		
	
	
	
	
	@Override
	public int saveProdukt(Produkt p) {
		try {
			produktRepository.save(p);
	        System.out.println("Verfübarkeit"+p.isVerfuebarkeit());

return 0; // in case where the save is successful 
			
		} catch (Exception e) {
	        System.out.println("Voici les erreurs"+e.getMessage());

		return 1;
		}
	}
	
	


	
	
	
	
	

	@Override
	public int deleteProdukt(int idProdukt) {
		try {
			produktRepository.deleteProdukt(idProdukt);// deletePersonne() kommt aus  PersonneRepository (Vererbung der PersonneRepository von LehrerRepository)
		return 0;
		} catch (Exception e) {
			return 1;
		}		
			}
	

	@Override
	public int updateProdukt(Produkt produkt) {
		try {
			Produkt updatedProdukt=this.produktRepository.save(produkt);
			return 0;
		} catch (Exception e) {
		return 1;
		}
	}

	@Override
	public List<Produkt> listProdukt() {
		return this.produktRepository.findAll();
	}

	@Override
	public Produkt getOneProduktByIdProdukt(int idProdukt) {
		return this.produktRepository.getProduktByIdProdukt(idProdukt);
		
	}

}
