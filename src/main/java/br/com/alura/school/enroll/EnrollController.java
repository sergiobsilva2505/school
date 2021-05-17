package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class EnrollController {


    private final EnrollRepository enrollRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    EnrollController(EnrollRepository enrollRepository){
        this.enrollRepository = enrollRepository;
    }

    @PostMapping("/courses/{code}/enroll")
    public ResponseEntity<?> saveEnroll(@Valid @PathVariable("code")String code, @RequestBody NewEnrollRequest obj){
        List<Enroll> enrolls = enrollRepository.findAll();
        Enroll enroll = findObjs(code, obj.getUserName());
        boolean exist = enrolls.stream().anyMatch(e -> e.equals(enroll));
        if (exist){
            throw new ResponseStatusException(BAD_REQUEST, format("User %s already enrolled in the course", obj.getUserName()));
        }
        Enroll obj2= enrollRepository.save(enroll);
        URI location = URI.create(format("/courses/enroll/%s", obj2.getId()));
        return ResponseEntity.created(location).build();
    }

    private Enroll findObjs(String code, String userName){
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", code)));
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User with name %s not found", userName)));
        Enroll enroll = new Enroll(user, course);
        return enroll;
    }

}
