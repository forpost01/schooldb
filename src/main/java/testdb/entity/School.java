package testdb.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by forpost on 13.03.17.
 */
@Entity
public class School {
    @Id
    private int school_id;
    @Column(unique = true, nullable = false, length = 200)
    private String address;
    @Column(nullable = false, length = 200)
    private String fioDirector;
    @Column(unique = true, nullable = false, length = 20)
    private String accountNumber;

    @OneToMany(mappedBy = "school",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    //@JoinTable(name = "classroom", joinColumns = { @JoinColumn(name = "school_id") })
    private List<Classroom> classrooms;

//    @OneToMany(cascade=CascadeType.ALL, mappedBy="school_id")
//    private List<Classroom> classrooms;

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFioDirector() {
        return fioDirector;
    }

    public void setFioDirector(String fioDirector) {
        this.fioDirector = fioDirector;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "School{" +
                "school_id=" + school_id +
                ", address='" + address + '\'' +
                ", fioDirector='" + fioDirector + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", classrooms=" + classrooms +
                '}';
    }
}
