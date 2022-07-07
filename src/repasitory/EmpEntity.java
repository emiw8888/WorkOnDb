package repasitory;

import java.util.Date;

public class EmpEntity {
    private int id;
    private final String name;
    private final String surname;
    private final long phone;
    private final int salary;
    private final Date createDate;
    private final boolean status;
    private final int deparmentId;

    public EmpEntity(int id, String name, String surname, long phone, int salary, Date createDate, boolean status, int deparmentId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.salary = salary;
        this.createDate = createDate;
        this.status = status;
        this.deparmentId = deparmentId;
    }

    public EmpEntity(String name, String surname, long phone, int salary, Date createDate, boolean status, int deparmentId) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.salary = salary;
        this.createDate = createDate;
        this.status = status;
        this.deparmentId = deparmentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public int getDeparmentId() {
        return deparmentId;
    }
}
