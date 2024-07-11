/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kavaa.tp5.flightmanagement.services;

import java.util.List;
import java.util.Optional;
import net.kavaa.tp5.flightmanagement.entity.Flight;
import net.kavaa.tp5.flightmanagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin Inclusiv 2
 */
@Service
public class FlightService {
   
    @Autowired
    private FlightRepository flightRepository;

    @Transactional(readOnly = true)
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Transactional
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Transactional
    public Optional<Flight> updateFlight(Long id, Flight flight) {
        Optional<Flight> existingFlight = flightRepository.findById(id);
        if (existingFlight.isPresent()) {
            Flight updatedFlight = existingFlight.get();
            updatedFlight.setFlightNumber(flight.getFlightNumber());
            updatedFlight.setDepartureDateTime(flight.getDepartureDateTime());
            updatedFlight.setArrivalDateTime(flight.getArrivalDateTime());
            updatedFlight.setDepartureCity(flight.getDepartureCity());
            updatedFlight.setArrivalCity(flight.getArrivalCity());
            return Optional.of(flightRepository.save(updatedFlight));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    } 
}
