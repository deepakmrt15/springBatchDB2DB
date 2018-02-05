package com.ds.db2db.example.domain.util;


import com.ds.db2db.example.domain.Employee;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ds on 11/14/2017.
 */

public class EmployeePreparedStatementSetter implements ItemPreparedStatementSetter<Employee> {

    public EmployeePreparedStatementSetter() {
    }

    @Override
    public void setValues(Employee emp,
                          PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, emp.getEmpId());
        preparedStatement.setString(2, emp.getName());
        preparedStatement.setString(3, emp.geteType());
        preparedStatement.setString(2, emp.geteStatus());
    }
}