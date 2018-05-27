package reflexion;

import java.util.*;

public class Person {
    private Integer personID; // ID человека
    private String name; // Имя
    private String lastname; // Фамилия
    private Date depositStartDate; // Дата начала вклада
    private Date depositEndDate; // Дата окончания вклада
    private Double depositSum; // Сумма вклада

    public Integer getPersonID() {
        return this.personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Skip
    public Date getDepositStartDate() {
        return this.depositStartDate;
    }
    @Skip
    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }
    @Skip
    public Date getDepositEndDate() {
        return this.depositEndDate;
    }
    @Skip
    public void setDepositEndDate(Date depositEndDate) {
        this.depositEndDate = depositEndDate;
    }
    @Skip
    public Double getDepositSum() {
        return this.depositSum;
    }
    @Skip
    public void setDepositSum(Double depositSum) {
        this.depositSum = depositSum;
    }
}
