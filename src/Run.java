import connection.ConnectionToDb;
import repasitory.DepEntity;
import repasitory.EmpEntity;
import services.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        Connection connection = ConnectionToDb.connect();
        try (connection){
            Services services = new Services(connection);

            services.getAllActiveEmployersByDeparmentId(1);
            System.out.println();

            services.getAllActiveDepartments();
            System.out.println();

            Map<String,Integer> map = services.averageSalaryForEachDepartment();
            System.out.println(map);
            System.out.println();

            EmpEntity emp1 = new EmpEntity("Revan","Hesenzade",553216612,4500,
                    Date.valueOf("2022-06-13"),true,1);
            services.updateEmployerById(emp1,3);

            List<EmpEntity> list = new ArrayList<>();
            EmpEntity emp2 = new EmpEntity("Senan","Hesimov",512345781,3000,
                    Date.valueOf("2018-12-21"),true,3);
            EmpEntity emp3 = new EmpEntity("Hemid","Huseynov",502353468,7000,
                    Date.valueOf("2009-02-29"),true,4);
            list.add(emp2);
            list.add(emp3);
            services.insertEmployee(list);

            DepEntity dep = new DepEntity("IT",Date.valueOf("2019-03-29"),true);
            services.updateDepartmentByDepartmentName(dep,"IT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
