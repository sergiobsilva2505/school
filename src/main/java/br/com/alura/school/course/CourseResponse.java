package br.com.alura.school.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class CourseResponse {

    @JsonProperty
    private final String code;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String shortDescription;

    CourseResponse(Course course) {
        this.code = course.getCode();
        this.name = course.getName();
        this.shortDescription = Optional.of(course.getDescription()).map(this::abbreviateDescription).orElse("");
    }

    private String abbreviateDescription(String description) {
        if (description.length() <= 13) return description;
        return description.substring(0, 10) + "...";
    }

    /**
     * Recebe uma lista de objetos do tipo Course como parametro,
     * faz a conversão para o tipo CourseResponse
     * e retorna nova lista deste objeto.
     * @param courses
     * @return
     */
    public static List<CourseResponse> toResponse(List<Course> courses) {
        return courses.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    public String getCode() {
        return code;
    }
}
