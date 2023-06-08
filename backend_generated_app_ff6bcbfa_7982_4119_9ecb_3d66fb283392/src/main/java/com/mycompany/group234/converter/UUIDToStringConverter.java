package com.mycompany.group234.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = false)
public class UUIDToStringConverter implements AttributeConverter<UUID, String> {
    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String s) {
        return s != null ? UUID.fromString(s): null;
    }
}