package com.mycompany.group234.enums;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmEnumeration;

@EdmEnumeration	  
public enum Feature{
	    Feature1,
	    Feature2,
	    Feature3,
	    Feature4; 
    public int value(Feature feature) {
        return feature.ordinal();
    }
    public static Feature getFeature(int ordinal) {
        for(Feature feature : Feature.values())
                if(feature.ordinal() == ordinal)
                        return feature;
        return null;
    }
}


