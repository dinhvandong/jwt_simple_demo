package vn.vti.moneypig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "vn.vti.moneypig")
@EnableMongoRepositories(basePackages = "vn.vti.moneypig.repositories")
public class MoneyPigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyPigApplication.class, args);
	}

}
