package com.EcomProductService;

import com.EcomProductService.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//@EnableElasticsearchRepositories
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class EcomProductServiceApplication implements CommandLineRunner {
    @Autowired
    private InitService initService;
	public static void main(String[] args) {
		SpringApplication.run(EcomProductServiceApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        initService.initialise();
    }
}
