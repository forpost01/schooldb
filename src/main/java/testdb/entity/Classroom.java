package testdb.entity;

import javax.persistence.*;

/**
 * Created by forpost on 13.03.17.
 */
@Entity
//@Table( name="Classroom",
//        uniqueConstraints=
//        @UniqueConstraint(columnNames={"CLASSNAME", "SCHOOL_ID"}))
public class Classroom {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int class_id;
    private String className;
    private int numberPupil;
    private String fioTeacher;

    //@ManyToOne (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    //@JoinColumn(name = "school_id", nullable = false)


    @ManyToOne
    //@JoinColumn(name="school_id", nullable=false)
    private School school;
    public School getSchool() { return school; }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getId() {
        return class_id;
    }

    public void setId(int class_id) {
        this.class_id = class_id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNumberPupil() {
        return numberPupil;
    }

    public void setNumberPupil(int numberPupil) {
        this.numberPupil = numberPupil;
    }

    public String getFioTeacher() {
        return fioTeacher;
    }

    public void setFioTeacher(String fioTeacher) {
        this.fioTeacher = fioTeacher;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + class_id +
                ", className='" + className + '\'' +
                ", numberPupil=" + numberPupil +
                ", fioTeacher='" + fioTeacher + '\'' +
                ", school=" + school.getAccountNumber() +
                '}';
    }
}
