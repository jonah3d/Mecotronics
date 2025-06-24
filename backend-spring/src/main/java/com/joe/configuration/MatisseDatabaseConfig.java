package com.joe.configuration;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtPackageObjectFactory;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MatisseDatabaseConfig {

    @Value("${matisse.host}")
    private String host;
    @Value("${matisse.database}")
    private String databaseName;

    @Value("${matisse.package}")
    private String javaPackage;

    @Value("${matisse.namespace}") // Optional, with a default value
    private String matissenamespace;

    @Bean
    @Primary
    // @Lazy(false) is not needed here as Spring will manage the lifecycle
    public MtDatabase mtDatabase() {
        if (host == null || databaseName == null || javaPackage == null) {
            throw new IllegalArgumentException("Matisse configuration properties are not set");
        }
        try {
            MtDatabase mt = new MtDatabase(host, databaseName,
                    new MtPackageObjectFactory(javaPackage, matissenamespace));
            mt.open();
            System.out.println("Connected to Matisse database: " + mt.getName());
            System.out.println("Host: " + mt.getHost());
            System.out.println("Namespace: " + mt.getSchemaNamespace());
            return mt;
        } catch (MtException e) {
            System.out.println("Error connecting to Matisse database: " + e.getMessage());
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    @PreDestroy
    public void closeDatabase() {
        // Add cleanup logic here if needed
    }
}
