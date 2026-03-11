package com.company.service;

import com.company.employee.Employee;
import com.company.exception.EmployeeNotFoundException;
import com.company.exception.InvalidSalaryException;

import java.io.*;

public class EmployeeService{

    private static final String FILE_NAME = "employees.txt";

    public void addEmployee(Employee e) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));

        bw.write(e.getEmpId() + "," +
                e.getName() + "," +
                e.getDepartment() + "," +
                e.getSalary());

        bw.newLine();
        bw.close();

        System.out.println("Employee Added Successfully");
    }

    public void displayEmployees() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            System.out.println("ID: " + data[0] +
                    " Name: " + data[1] +
                    " Department: " + data[2] +
                    " Salary: " + data[3]);
        }

        br.close();
    }

    public void searchEmployee(int empId)
            throws IOException, EmployeeNotFoundException {

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (Integer.parseInt(data[0]) == empId) {

                System.out.println("Employee Found");
                System.out.println("ID: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Department: " + data[2]);
                System.out.println("Salary: " + data[3]);

                found = true;
                break;
            }
        }

        br.close();

        if (!found) {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }
}