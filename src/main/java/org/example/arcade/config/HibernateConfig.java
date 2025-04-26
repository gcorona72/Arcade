// src/main/java/org/example/arcade/config/HibernateConfig.java
package org.example.arcade.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        // Convertimos el EntityManagerFactory de Spring en SessionFactory de Hibernate
        return emf.unwrap(SessionFactory.class);
    }
}
