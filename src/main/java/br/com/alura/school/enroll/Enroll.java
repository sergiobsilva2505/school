package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate enrollDate;

    @ManyToOne
    @NotBlank
    private User user;

    @ManyToOne
    @NotBlank
    private Course course;

    @Deprecated
    protected Enroll(){}

    Enroll(LocalDate enrollDate, User user, Course course) {
        this.enrollDate = enrollDate;
        this.user = user;
        this.course = course;
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
}
