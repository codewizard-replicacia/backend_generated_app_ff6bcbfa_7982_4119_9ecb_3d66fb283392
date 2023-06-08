package com.mycompany.group234.repository;


import com.mycompany.group234.model.Airport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class AirportRepository extends SimpleJpaRepository<Airport, String> {
    private final EntityManager em;
    public AirportRepository(EntityManager em) {
        super(Airport.class, em);
        this.em = em;
    }
    @Override
    public List<Airport> findAll() {
        return em.createNativeQuery("Select * from \"generated_app\".\"Airport\"", Airport.class).getResultList();
    }
}