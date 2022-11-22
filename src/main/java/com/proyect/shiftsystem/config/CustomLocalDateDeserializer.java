package com.proyect.shiftsystem.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.proyect.shiftsystem.excepcion.BadRequestException;
import io.vavr.control.Try;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateDeserializer extends JsonDeserializer {
    // deserializador: convierte de un determinado formato a otro en este caso formato fecha
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = jsonParser.getText();
        Try.of(()->{
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;}).getOrElseThrow(()-> new BadRequestException("el formato de fecha no es aceptado (ej: 22-02-2022)"));
        return null;
        }
}
