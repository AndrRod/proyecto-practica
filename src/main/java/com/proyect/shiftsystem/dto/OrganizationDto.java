package com.proyect.shiftsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.proyect.shiftsystem.config.CustomLocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
//json include nos permite ocultar atributos que son nulos
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDto {
    private Long id;
    private String name;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate shiftDate;
    private LocalTime shiftHourDate;
}
