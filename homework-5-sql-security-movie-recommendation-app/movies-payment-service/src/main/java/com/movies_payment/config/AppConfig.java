package com.movies_payment.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {
    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        JdbcTemplate template = new JdbcTemplate(hikariDataSource);
        template.execute("""
                create table if not exists payment
                (
                    payment_id   serial
                        constraint payments_pk
                            primary key,
                    user_id      int,
                    payment_type int,
                    payment_date timestamp
                );
                """);
        return template;
    }
}