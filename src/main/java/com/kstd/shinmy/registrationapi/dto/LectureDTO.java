package com.kstd.shinmy.registrationapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureDTO {
    private long lectureId;

    @JsonProperty(required = true)
    private int capacity;

    @JsonProperty(required = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    private String lecturer;

    private String hall;

    private String subject;
}
