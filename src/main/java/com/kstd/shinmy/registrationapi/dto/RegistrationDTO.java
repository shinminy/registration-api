package com.kstd.shinmy.registrationapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrationDTO {
    private Long registrationId;

    @JsonProperty(required = true)
    private long lectureId;

    @JsonProperty(required = true)
    private String employeeIdNumber;
}
