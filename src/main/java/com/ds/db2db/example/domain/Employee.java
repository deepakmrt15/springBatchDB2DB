package com.ds.db2db.example.domain;

/**
 * Created by sharmd01 on 11/12/2017.
 */
public class Employee {
    String empId;
    String name;
    String eType;

    public String geteType() {
        return eType;
    }

    public void seteType(String eType) {
        this.eType = eType;
    }

    public String geteStatus() {
        return eStatus;
    }

    public void seteStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    String eStatus;

//EMP_ID, EMP_NAME, EMP_TYPE, EMP_STATUS

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
