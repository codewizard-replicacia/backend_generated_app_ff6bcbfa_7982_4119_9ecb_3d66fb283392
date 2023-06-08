package com.mycompany.group234.enums;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmEnumeration;

@EdmEnumeration	  
public enum PersonGender{
	    Male,
	    Female,
	    Unknown; 
    public int value(PersonGender personGender) {
        return personGender.ordinal();
    }
    public static PersonGender getPersonGender(int ordinal) {
        for(PersonGender personGender : PersonGender.values())
                if(personGender.ordinal() == ordinal)
                        return personGender;
        return null;
    }
}


