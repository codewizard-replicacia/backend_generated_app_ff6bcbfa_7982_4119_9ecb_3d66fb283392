package com.mycompany.group234.converter;

import com.mycompany.group234.enums.PersonGender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class PersonGenderConverter implements AttributeConverter<PersonGender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PersonGender personGender) {
        return personGender != null ? personGender.ordinal() : null;
    }

    @Override
    public PersonGender convertToEntityAttribute(Integer dbData) {
		return PersonGender.getPersonGender(dbData);
    }
}
