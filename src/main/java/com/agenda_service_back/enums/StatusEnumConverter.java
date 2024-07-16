package com.agenda_service_back.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getCodigo();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        return StatusEnum.toEnum(codigo);
    }
}

