package schoolManager.entity;

import javax.persistence.*;

/**
 * Created by forpost on 13.03.17.
 */
@Entity
//@Table( name="Classroom",
//        uniqueConstraints=
//        @UniqueConstraint(columnNames={"CLASSNAME", "SCHOOL_ID"}))
//@NamedQuery(name="Classroom.findBySchoolAndName", query="select e from Classroom e " +
//        "where e.classname=:name") // and e.school_school_id=:id
public class Classroom {
    public Classroom() {
    }

    public Classroom(String className, int numberPupil, String fioTeacher, School school) {
        this.className = className;
        this.numberPupil = numberPupil;
        this.fioTeacher = fioTeacher;
        this.school = school;
    }

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int class_id;
    private String className;
    private int numberPupil;
    private String fioTeacher;

    @ManyToOne
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
                ", school=" + school.getSchool_id() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classroom that = (Classroom) o;

        if (numberPupil != that.numberPupil) return false;
        if (school.getSchool_id() != that.school.getSchool_id()) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (fioTeacher != null ? !fioTeacher.equals(that.fioTeacher) : that.fioTeacher != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = school.getSchool_id();
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (fioTeacher != null ? fioTeacher.hashCode() : 0);
        result = 31 * result + numberPupil;
        return result;
    }
}
