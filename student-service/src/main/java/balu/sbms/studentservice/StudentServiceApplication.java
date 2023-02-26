package balu.sbms.studentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"balu.sbms.studentservice.controller",
        "balu.sbms.studentservice.service"})
@EntityScan("balu.sbms.studentservice.entity")
@EnableJpaRepositories("balu.sbms.studentservice.repository")
public class StudentServiceApplication {

    @Value("${address.service.url}")
    private String addressServiceURL;

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(addressServiceURL).build();
        return webClient;
    }
}
