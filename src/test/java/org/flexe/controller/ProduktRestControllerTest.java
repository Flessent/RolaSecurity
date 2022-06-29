package org.flexe.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.flexe.Produkt;
import org.flexe.dto.ProduktDTO;
import org.flexe.repository.ProduktRepository;
import org.flexe.serviceImpl.ProduktServicesImpl;
import org.flexe.services.ProduktService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProduktRestControllerTest {

	private ProduktServicesImpl prduktServicesImpl ;
    @Autowired
	private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Mock
    private ProduktService produktService;//add mock of the ProduktService to the ApplicationContext.*/
    @InjectMocks
    private ProduktRestController produktRestController;
	@Mock
	private ProduktRepository produktRepository;
	  @BeforeEach
	    public void setup() {
		
		  Mockito.reset(produktService);
		  MockMvcBuilders
          .webAppContextSetup(webApplicationContext)
          .apply(springSecurity())
          .build();
		  
	    }
	  
	  
	  
	  
	  
	  private ProduktDTO produktDTO= new ProduktDTO(123, "MilchTest1",12,23,false);
;

		@WithMockUser("spring")
		@Test
		public void saveProduktTest() throws Exception{
		/*
		 Die Ausführung dieses Test zweimal führt zum Fehler ,denn es gibt ein CONSTRAINT UnicityProduktName UNIQUE(name)
		 * */
		  
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/Produkt/saveProdukt")
					.content(asJsonString(produktDTO))
				      .contentType(MediaType.APPLICATION_JSON_VALUE)
				      .accept(MediaType.APPLICATION_JSON_VALUE)							
				      .with(user("flessent@gov.de").password("1234567890").roles("ADMIN"))
					.accept(MediaType.APPLICATION_JSON_VALUE);
			ResultActions resultActions= mockMvc.perform(requestBuilder)
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andDo(print());
				}
		
		
		@Test
		@WithMockUser("spring")
		
		public void updateProduktTest() throws Exception {

	     
	      produktDTO.setName("CurrywurstTest");
			RequestBuilder requestBuilder = MockMvcRequestBuilders.patch( "/Produkt/updateProdukt")
					.content(asJsonString(produktDTO))
				      .contentType(MediaType.APPLICATION_JSON_VALUE)
				      .accept(MediaType.APPLICATION_JSON_VALUE)							
				      .with(user("flessent@gov.de").password("1234567890").roles("ADMIN"))
					.accept(MediaType.APPLICATION_JSON_VALUE);
			ResultActions resultActions= mockMvc.perform(requestBuilder)
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(print());
		}
		@Test
		@WithMockUser("spring")
		@AfterEach
		public  void  deleteProduktTest() throws Exception {
			RequestBuilder requestBuilder=MockMvcRequestBuilders.delete("/Produkt/deletePrdukt/123")
					.accept(MediaType.APPLICATION_JSON);
			ResultActions resultActions = mockMvc.perform(requestBuilder)
					.andExpect(MockMvcResultMatchers.status().isOk());

		}	  

		
		@WithMockUser("spring")
		@Test
		public void listProduktTest() throws Exception {
			List<Produkt> listeProdukt = produktService.listProdukt();
			Mockito.when(produktService.listProdukt()).thenReturn(listeProdukt);
			//listeLehrer.stream().map(lehrer-> this.lehrerServicesImplementation.convertEntityToDto(lehrer)).collect(Collectors.toList());

			RequestBuilder requestBuilder =MockMvcRequestBuilders.get( "/Produkt/listProdukt")
				      .with(user("flessent@gov.de").password("1234567890").roles("ADMIN"))
					.accept(MediaType.APPLICATION_JSON);
	              ResultActions resultActions=   mockMvc.perform(requestBuilder)            		  
	        		.andExpect(MockMvcResultMatchers.status().isOk() )
	        		.andDo(print());
	        		
		}
		
		
		
		public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
}
