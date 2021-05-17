package br.com.alura.school.enroll;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class NewEnrollRequest {

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    @JsonProperty
    private String userName;

    public NewEnrollRequest() {
    }

    public String getUserName() {
        return userName;
    }


}
