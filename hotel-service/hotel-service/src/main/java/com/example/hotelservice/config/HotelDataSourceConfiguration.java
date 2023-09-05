package com.example.hotelservice.config;

import com.example.hotelservice.entities.Hotel;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.inject.Qualifier;
import javax.sql.DataSource;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableJpaRepositories(basePackages = "come.example.hotelservice.repositories")
public class HotelDataSourceConfiguration {

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.hotel")
  public DataSourceProperties hotelDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.hotel.configuration")
  public DataSource hotelDataSource() {
    return hotelDataSourceProperties().initializeDataSourceBuilder()
        .type(HikariDataSource.class).build();
  }




}
