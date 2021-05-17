package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate enrollDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Deprecated
    protected Enroll(){}

    Enroll(User user, Course course) {
        this.enrollDate = LocalDate.now();
        this.user = user;
        this.course = course;
    }

    Long getId() {
        return id;
    }

    LocalDate getEnrollDate() {
        return enrollDate;
    }

    User getUser() {
        return user;
    }

    Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enroll)) return false;
        Enroll enroll = (Enroll) o;
        return Objects.equals(getUser(), enroll.getUser()) && Objects.equals(getCourse(), enroll.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getCourse());
    }
}
