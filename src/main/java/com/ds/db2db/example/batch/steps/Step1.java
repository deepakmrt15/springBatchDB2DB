package com.ds.db2db.example.batch.steps;

import com.ds.db2db.example.domain.Employee;
import com.ds.db2db.example.domain.util.EmployeePreparedStatementSetter;
import com.ds.db2db.example.domain.util.EmployeeRowMapper;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.*;

import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;

import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


/**
 * Created by ds on 2017-11-15.
 */
//@Configuration
public class Step1 {

    /**
     *This is is to read data from input datasource
     */
    @Bean
    public ItemReader jdbcReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Employee>()
                .dataSource(dataSource)
                .name("test")
                .sql("SELECT EMP_ID empId, NAME name  FROM TEST.EMPLOYEE;")
                .rowMapper(new EmployeeRowMapper())
                .build();
    }


    @Bean
    public JdbcBatchItemWriter<Employee> jdbcWriter(DataSource targetdatasource) {

        String QUERY_INSERT =" INSERT INTO scott.EMPLOYEE( EMPID, NAME) values (?, ? ) ";
        JdbcBatchItemWriter<Employee> databaseItemWriter = new JdbcBatchItemWriter<Employee>();
        databaseItemWriter.setDataSource(targetdatasource);
        databaseItemWriter.setSql(QUERY_INSERT);

        ItemPreparedStatementSetter<Employee> valueSetter =
                new EmployeePreparedStatementSetter();
        databaseItemWriter.setItemPreparedStatementSetter(valueSetter);


        return databaseItemWriter;


    }
}
