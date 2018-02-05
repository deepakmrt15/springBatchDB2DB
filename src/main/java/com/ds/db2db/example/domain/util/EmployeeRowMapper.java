package com.ds.db2db.example.domain.util;

/**
 * Created by sharmd01 on 11/14/2017.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ds.db2db.example.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        // ,

        Employee emp = new Employee();

        emp.setEmpId(rs.getString("EMP_ID"));
        emp.setName(rs.getString("EMP_NAME"));
        emp.seteType(rs.getString("EMP_TYPE"));
        emp.seteStatus(rs.getString("EMP_STATUS"));

        return emp;
    }
}
