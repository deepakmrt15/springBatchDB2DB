package com.ds.db2db.example.batch.steps;


import com.ds.db2db.example.domain.*;
import com.ds.db2db.example.domain.util.EmployeePreparedStatementSetter;
import com.ds.db2db.example.domain.util.EmployeeRowMapper;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
/**
 * Created by ds on 12/23/2017.
 */
@Configuration
public class EmployeeBatchStep {

    /**
     *This is is to read data from input datasource
     */
@Autowired
    JobRepository jobRepository;
    @Bean
    public ItemReader jdbcReader(DataSource dataSource) {

        JdbcCursorItemReader<Employee> jdbcCursorItemReader = new JdbcCursorItemReader<Employee>();
        jdbcCursorItemReader.setDataSource(dataSource);
      //  System.out.println("dataSource: "+dataSource.toString());
        jdbcCursorItemReader.setSql(" SELECT EMP_ID, EMP_NAME, EMP_TYPE, EMP_STATUS FROM EMPLOYEE ");
        jdbcCursorItemReader.setVerifyCursorPosition(false);
        jdbcCursorItemReader.setRowMapper(new EmployeeRowMapper());
        return jdbcCursorItemReader;
    }

    /**
     *This is is to insert data into target datasource
     */
    @Bean
    @Transactional(isolation = Isolation.DEFAULT)
    public JdbcBatchItemWriter<Employee> jdbcWriter(DataSource targetdatasource) {

        String QUERY_INSERT =" INSERT INTO SCOTT.EMPLOYEE( EMP_ID, EMP_NAME, EMP_TYPE, EMP_STATUS ) values (?, ?, ?, ? ) ";
        JdbcBatchItemWriter<Employee> databaseItemWriter = new JdbcBatchItemWriter<Employee>();
        databaseItemWriter.setDataSource(targetdatasource);
        //System.out.println("dataSource: "+targetdatasource.toString());
        databaseItemWriter.setSql(QUERY_INSERT);
                ItemPreparedStatementSetter<Employee> valueSetter =
                new EmployeePreparedStatementSetter() ;
        databaseItemWriter.setItemPreparedStatementSetter(valueSetter);


        return databaseItemWriter;


    }

}
