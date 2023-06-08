package com.mycompany.group234.function;

import com.mycompany.group234.model.Person;
import com.mycompany.group234.model.Document;
import com.mycompany.group234.model.Airline;
import com.mycompany.group234.model.Airport;
import com.mycompany.group234.model.Trip;
import com.mycompany.group234.model.PlanItem;
import com.mycompany.group234.model.Event;
import com.mycompany.group234.model.PublicTransportation;
import com.mycompany.group234.model.Flight;
import com.mycompany.group234.model.Employee;
import com.mycompany.group234.model.Manager;




import com.mycompany.group234.model.complex.Location;
import com.mycompany.group234.model.complex.City;
import com.mycompany.group234.model.complex.AirportLocation;
import com.mycompany.group234.model.complex.EventLocation;
import com.mycompany.group234.enums.PersonGender;
import com.mycompany.group234.enums.Feature;
import com.mycompany.group234.converter.PersonGenderConverter;
import com.mycompany.group234.converter.FeatureConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmAction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataAction;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Component
public class JavaActions implements ODataAction {
    private final EntityManager entityManager;

    public JavaActions(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	
	
	    
	    @EdmAction(name = "ResetDataSource", isBound = false)
	    public void resetDataSource() {
	        EntityTransaction entityTransaction = entityManager.getTransaction();
	        entityTransaction.begin();
            		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Trip\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Airline\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Flight\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"PlanItem\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Document\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Manager\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"AbstractPerson\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Employee\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"AbstractPlanItem\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Airport\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Event\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"PublicTransportation\"").executeUpdate();
    		entityManager.createNativeQuery("DELETE FROM \"Trippin\".\"Person\"").executeUpdate();
        entityTransaction.commit();
    
	    
	    

      }     
	    
          @EdmAction(name = "ShareTrip", isBound = true)
		  public void ShareTrip(    
			 @EdmParameter(name = "personInstance", isNullable = true) final Person personInstance , 
		 
			 @EdmParameter(name = "userName", isNullable = false) final String userName , 
		 
			 @EdmParameter(name = "tripId", isNullable = false) final Integer tripId ){ 
			 //customlogic
 
		 
      }     
	    
          @EdmAction(name = "UpdateLastName", isBound = true)
		  public boolean UpdateLastName(    
			 @EdmParameter(name = "person", isNullable = true) final Person person ,  
			 
			 @EdmParameter(name = "lastName", isNullable = false) final String lastName ){ 
			 //customlogic
			 return false;
  
			 
	  
		     
           
      }     
}
  