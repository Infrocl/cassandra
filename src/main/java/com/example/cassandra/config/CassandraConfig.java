package com.example.cassandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace}")
    private String keyspace;
    @Value("${spring.data.cassandra.local-datacenter}")
    private String datacenter;
    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;
    @Value("${spring.data.cassandra.port}")
    private int port;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getLocalDataCenter() {
        return datacenter;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Bean
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        final CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints(contactPoints);
        cqlSessionFactoryBean.setKeyspaceName(keyspace);
        cqlSessionFactoryBean.setLocalDatacenter(datacenter);
        cqlSessionFactoryBean.setPort(port);
        return cqlSessionFactoryBean;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Arrays.asList(
                CreateKeyspaceSpecification.createKeyspace(keyspace)
                        .ifNotExists(true)
                        .with(KeyspaceOption.DURABLE_WRITES, true));
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.example.cassandra"};
    }

//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return List.of(DropKeyspaceSpecification.dropKeyspace(keyspace));
//    }
}