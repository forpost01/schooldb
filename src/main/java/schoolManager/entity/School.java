package schoolManager.entity;

import javax.persistence.*;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by forpost on 13.03.17.
 */
@Entity
@XmlRootElement(name = "school")
//@XmlAccessorType(XmlAccessType.FIELD)
public class School {
    public School() {
    }

    public School(String address, String fioDirector, String accountNumber) {
        this.address = address;
        this.fioDirector = fioDirector;
        this.accountNumber = accountNumber;
    }

    @Id
    private int school_id;
    @Column(unique = true, nullable = false, length = 200)
    private String address;
    @Column(nullable = false, length = 200)
    private String fioDirector;
    @Column(unique = true, nullable = false, length = 20)
    private String accountNumber;
    @OneToMany(mappedBy = "school",fetch = FetchType.EAGER, orphanRemoval=true)
    private List<Classroom> classrooms;
    @XmlElement
    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @XmlAttribute
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
                //", classrooms=" + classrooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School that = (School) o;

        if (school_id != that.school_id) return false;
        if (fioDirector != null ? !fioDirector.equals(that.fioDirector) : that.fioDirector != null) return false;
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = school_id;
        result = 31 * result + (fioDirector != null ? fioDirector.hashCode() : 0);
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}
