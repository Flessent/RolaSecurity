package org.flexe;

import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@SpringBootApplication
public class ProbeCodeRolaEnterpriseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProbeCodeRolaEnterpriseApplication.class, args);
	}
	
	
	
	 @Bean
     public ModelMapper modelMapper() {
         final ModelMapper mapper = new ModelMapper();
         Provider<UUID> uuidProvider = new AbstractProvider<UUID>() {
             @Override
             public UUID get() {
                 return UUID.randomUUID();
             }
         }; 

         final Converter<String, UUID> uuidConverter = new AbstractConverter<>() {
             @Override
             protected UUID convert(final String source) {
                 return UUID.fromString(source);
             }
         };
         mapper.createTypeMap(String.class, UUID.class);
         mapper.addConverter(uuidConverter);
         mapper.getTypeMap(String.class, UUID.class).setProvider(uuidProvider);


         return mapper;
     }
	

}
