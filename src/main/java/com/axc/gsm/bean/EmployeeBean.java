package com.axc.gsm.bean;

import com.axc.gsm.model.Employee;
import org.fluttercode.datafactory.impl.DataFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class EmployeeBean {

    private List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {

        DataFactory dataFactory = new DataFactory();

        for (int i = 1; i < 20; i++) {

            Employee employee = new Employee();

            employee.setId(i);

            employee.setName(dataFactory.getName());

            employee.setPhoneNumber(String.format("%s-%s-%s",
                    dataFactory.getNumberText(3),
                    dataFactory.getNumberText(3),
                    dataFactory.getNumberText(4)));

            employee.setAddress(dataFactory.getAddress() + "," + dataFactory.getCity());

            employeeList.add(employee);
        }
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

}