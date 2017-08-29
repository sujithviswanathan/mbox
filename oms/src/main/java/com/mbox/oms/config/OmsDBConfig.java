package com.mbox.oms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
@Configuration
public class OmsDBConfig {
    @Autowired
    Environment environment;
   public static Logger LOGGER = LoggerFactory.getLogger(OmsDBConfig.class);
    @Bean
    public DataSource dataSource(){
        LOGGER.info("Creating Datasource ..");
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db =databaseBuilder.setType(EmbeddedDatabaseType.DERBY).
                addScript(environment.getProperty("oms.db.sql")).
        build();
        LOGGER.info("Datasource created.");
        return db;

    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

}
