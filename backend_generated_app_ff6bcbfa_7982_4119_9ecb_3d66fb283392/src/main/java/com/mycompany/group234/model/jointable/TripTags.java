package com.mycompany.group234.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

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

@Entity(name = "TripTags")
@Table(schema = "\"generated_app\"", name = "\"TripTags\"")
@Data
public class TripTags{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"TripId\"")
	private Integer tripId;

    
    @Column(name = "\"Tags\"")
    private Integer tags;
}