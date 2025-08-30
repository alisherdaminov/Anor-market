package Anor.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AnorMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnorMarketApplication.class, args);
	}

}
