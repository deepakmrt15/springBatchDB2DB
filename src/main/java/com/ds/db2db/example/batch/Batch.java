package com.ds.db2db.example.batch;

import com.ds.db2db.example.batch.steps.EmployeeBatchStep;
//import Step1;
import com.ds.db2db.example.config.DatasourceConfiguration;
import com.ds.db2db.example.domain.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Created by ds on 2017-11-15.
 */
@Configuration
@EnableBatchProcessing
@Import(DatasourceConfiguration.class)
@ComponentScan(basePackageClasses = DatasourceConfiguration.class)
public class Batch {

 //this class executes the batch job to read data from 'datasource' and loads into to the db 'secondaryDataSource'.
    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    public DataSource secondaryDataSource;
    @Bean
    Job job(JobBuilderFactory jbf,
            StepBuilderFactory sbf,
            EmployeeBatchStep step1) throws Exception {

        Step s1 = sbf.get("db-db")
                .<Employee, Employee>chunk(100)
                .reader(step1.jdbcReader(dataSource))
                .writer(step1.jdbcWriter(secondaryDataSource))
                .build();

        return jbf.get("dataMigration from oneDb->AnotherDB")
                .incrementer(new RunIdIncrementer())
                .start(s1)
                .build();

    }
}
