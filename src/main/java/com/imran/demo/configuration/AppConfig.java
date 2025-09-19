package com.imran.demo.configuration;

import com.imran.demo.DB;
import com.imran.demo.DevDB;
import com.imran.demo.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean // it supply objects & handle objects
    @ConditionalOnProperty(name = "project.mode",havingValue = "Development")
    public DB getDevDBBean(){
        return new DevDB();
    }

    @Bean
    @ConditionalOnProperty(name = "project.mode",havingValue = "Production")
    public DB getProdDBBean(){
        return  new ProdDB();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }
}
