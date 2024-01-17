package vn.vti.moneypig.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.vti.moneypig.models.Service;
import vn.vti.moneypig.repositories.ServiceRepository;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(ServiceRepository serviceRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if(serviceRepository.findAll().isEmpty()){
                    Service service1 = new Service();
                    service1.setId(1L);
                    service1.setServiceCode("C-HA123");
                    service1.setServiceName("ABC");
                    service1.setServiceType("HHH");
                    serviceRepository.insert(service1);
                }
            }
        };
    }
    
}
