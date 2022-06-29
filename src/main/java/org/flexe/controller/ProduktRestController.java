package org.flexe.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.flexe.dto.ProduktDTO;
import org.flexe.services.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






@Secured(value = "ADMIN")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Produkt")

public class ProduktRestController {



@Autowired
private ProduktService produktService;

@PostMapping(path = "/saveProdukt" , produces =  MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Integer> saveProdukt(@RequestBody @Valid ProduktDTO produktDTO) {
		System.out.println("produktDTO :"+produktDTO);
		int back=produktService.saveProdukt( this.produktService.convertDtoToEntity(produktDTO));
		return new ResponseEntity<Integer>(back, HttpStatus.CREATED);

	}
	
	@GetMapping(path = "/listProdukt")
	public ResponseEntity<List<ProduktDTO>>  listeprodukt(){
		 List<ProduktDTO> listProduktDTO =produktService.listProdukt().stream().map(produkt ->produktService.convertEntityToDto(produkt)  ).collect(Collectors.toList());
			System.out.println("liste ist"+produktService.listProdukt());
				 return new ResponseEntity<>(listProduktDTO,HttpStatus.OK);
	}
	
	@PatchMapping(path = "/updateProdukt")
	public ResponseEntity<?> updateprodukt( @RequestBody ProduktDTO produktDTO){
		System.out.print("voici le ProduktDTO arrivant"+produktDTO);
		 return new  ResponseEntity<>(produktService.updateProdukt(produktService.convertDtoToEntity(produktDTO)), HttpStatus.OK);
	}
	@DeleteMapping(path = "/deleteProdukt/{idProdukt}", produces = "application/json")
	public ResponseEntity<?> deleteProdukt(@PathVariable("idProdukt") int idProdukt){
		produktService.deleteProdukt(idProdukt);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(path = "/getProduktByIdProdukt/{idProdukt}")
	public ResponseEntity<ProduktDTO> getOneProduktByIdProdukt(@PathVariable("idProdukt")  int idProdukt){
		
		ProduktDTO produktDTO=this.produktService.convertEntityToDto(produktService.getOneProduktByIdProdukt(idProdukt));
		System.out.print("get one ProduktDTO "+produktDTO);

	 return new ResponseEntity<>(produktDTO,HttpStatus.OK); 
		
	}
}
