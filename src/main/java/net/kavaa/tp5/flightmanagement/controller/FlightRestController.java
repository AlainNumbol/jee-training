/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package net.kavaa.tp5.flightmanagement.controller;

import java.util.List;
import java.util.Optional;
import net.kavaa.tp5.flightmanagement.entity.Flight;
import net.kavaa.tp5.flightmanagement.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin Inclusiv 2
 */
@RestController
@RequestMapping("/rest/flight")
public class FlightRestController {
    @Autowired
    private FlightService flightService;
    
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Optional<Flight> updatedFlight = flightService.updateFlight(id, flight);
        return updatedFlight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
    
}
