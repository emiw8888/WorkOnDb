package services;

import repasitory.DepEntity;
import repasitory.EmpEntity;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Services {
    Connection c;

    public Services(Connection c) {
        this.c = c;
    }

    public void getAllActiveDepartments() throws SQLException {
        Statement stmt = c.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * from department where status = true");
        while (resultSet.next()) {
            System.out.println("Name: " + resultSet.getString("department_name"));
            System.out.println("Create date: " + resultSet.getDate("create_date"));
            System.out.println("Status: " + resultSet.getBoolean("status"));
            System.out.println();
        }
    }

    public void getAllActiveEmployersByDeparmentId(int id) throws SQLException {
        PreparedStatement stmt = c.prepareStatement("SELECT * from employer where status = true and department_id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            System.out.println("Name: " + resultSet.getString("emp_name"));
            System.out.println("Surname: " + resultSet.getString("emp_surname"));
            System.out.println("Phone: " + resultSet.getLong("phone"));
            System.out.println("Salary: " + resultSet.getInt("salary"));
            System.out.println("Create date: " + resultSet.getDate("create_date"));
            System.out.println("Status: " + resultSet.getBoolean("status"));
            System.out.println("Deparment id: " + resultSet.getInt("department_id"));
            System.out.println();
        }
    }

    public void updateEmployerById(EmpEntity emp, int id) throws SQLException {
        PreparedStatement stmt = c.prepareStatement("update employer set emp_name = ?," +
                "emp_surname = ?,phone = ?,salary = ?,create_date = ?,status = ?,department_id = ?" +
                "where id = ?");
        stmt.setString(1, emp.getName());
        stmt.setString(2, emp.getSurname());
        stmt.setLong(3, emp.getPhone());
        stmt.setInt(4, emp.getSalary());
        stmt.setDate(5, (Date) emp.getCreateDate());
        stmt.setBoolean(6, emp.isStatus());
        stmt.setInt(7, emp.getDeparmentId());
        stmt.setInt(8,id);
        stmt.executeUpdate();

        System.out.println("updated succesfully");

    }

    public Map<String, Integer> averageSalaryForEachDepartment() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("select avg(e.salary) average,d.department_name departmentName from employer e " +
                "join department d" +
                " on e.department_id = d.id " +
                "group by d.department_name  ");
        while (rs.next()) {
            map.put(rs.getString("departmentName"), rs.getInt("average"));
        }
        return map;
    }

    public void insertEmployee(List<EmpEntity> e) throws SQLException {
        PreparedStatement stmt = null;
        for (EmpEntity emp : e) {
            stmt = c.prepareStatement("insert into employer " +
                    "(emp_name,emp_surname,phone,salary,create_date,status,department_id) values (?,?,?,?,?,?,?);");
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getSurname());
            stmt.setLong(3, emp.getPhone());
            stmt.setInt(4, emp.getSalary());
            stmt.setDate(5, (Date) emp.getCreateDate());
            stmt.setBoolean(6, emp.isStatus());
            stmt.setInt(7, emp.getDeparmentId());
            stmt.executeUpdate();
        }
        System.out.println("inserted succesfully");
    }

    public void updateDepartmentByDepartmentName(DepEntity dep , String departmentName) throws SQLException {
        PreparedStatement stmt = c.prepareStatement("update department set department_name = ?," +
                "create_date = ?,status = ? where department_name = ?");
        stmt.setString(1, dep.getDepName());
        stmt.setDate(2, (Date) dep.getCreateDate());
        stmt.setBoolean(3, dep.isStatus());
        stmt.setString(4, departmentName);
        stmt.executeUpdate();

        System.out.println("updated succesfully");
    }
}
