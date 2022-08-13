package backend.sellerB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;


@SpringBootApplication
public class SellerBApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:aws.yml";
	public static void main(String[] args) {
//		SpringApplication.run(SellerBApplication.class, args);
		new SpringApplicationBuilder(SellerBApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}


	//Spring Security와 함께
//	@Bean
//	public AuditorAware<String> auditorProvider(){
//		return()-> Optional.of(UUID.randomUUID().toString());
//	}

}
