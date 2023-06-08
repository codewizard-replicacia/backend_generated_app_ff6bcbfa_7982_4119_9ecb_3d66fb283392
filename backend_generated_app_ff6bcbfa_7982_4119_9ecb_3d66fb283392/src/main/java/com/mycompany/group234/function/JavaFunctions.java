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
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.mycompany.group234.repository.TripRepository;
import com.mycompany.group234.repository.AirlineRepository;
import com.mycompany.group234.repository.AbstractPlanItemRepository;
import com.mycompany.group234.repository.AirportRepository;
import com.mycompany.group234.repository.DocumentRepository;
import com.mycompany.group234.repository.AbstractPersonRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
		    @EdmFunction(name = "GetFriendsTrips", returnType = @EdmFunction.ReturnType(isCollection = true, type = Trip.class), hasFunctionImport = true, isBound = true )
		    public List<Trip> GetFriendsTrips(
			      @EdmParameter(name = "person") final Person person ,       
			      @EdmParameter(name = "userName") final String userName ){
			      return null;
			      }      
    
		    @EdmFunction(name = "GetPersonWithMostFriends", returnType = @EdmFunction.ReturnType, hasFunctionImport = true, isBound = false )
		    public Person GetPersonWithMostFriends(){
		           return null;
			      }
			      
            
    
		    @EdmFunction(name = "GetInvolvedPeople", returnType = @EdmFunction.ReturnType(isCollection = true, type = Person.class), hasFunctionImport = true, isBound = true )
		    public List<Person> GetInvolvedPeople(
			      @EdmParameter(name = "trip") final Trip trip ){
			      return null;
			      }      
    
		    @EdmFunction(name = "GetNearestAirport", returnType = @EdmFunction.ReturnType, hasFunctionImport = true, isBound = false )
		    public Airport GetNearestAirport(
			      @EdmParameter(name = "lat") final Double lat , 
			      
			      @EdmParameter(name = "lon") final Double lon ){
			      return null;
			      }
			      
    
		    @EdmFunction(name = "GetFavoriteAirline", returnType = @EdmFunction.ReturnType, hasFunctionImport = true, isBound = true )
		    public Airline GetFavoriteAirline(
			      @EdmParameter(name = "person") final Person person ){
			      return null;
			      }      
    
}
   
