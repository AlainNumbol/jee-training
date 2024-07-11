/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package net.kavaa.tp5.flightmanagement.controller;

import java.util.List;
import net.kavaa.tp5.flightmanagement.entity.Flight;
import net.kavaa.tp5.flightmanagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin Inclusiv 2
 */
@Controller
@RequestMapping("/flights")
public class FlightController {
    
    private final FlightRepository flightRepository;
    private final ConversionService conversionService;
    
    @Autowired
    public FlightController(FlightRepository flightRepository, ConversionService conversionService) {
        this.flightRepository = flightRepository;
        this.conversionService = conversionService;
    }
    
    @GetMapping("/new")
    public String showNewFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/new";
    }
    
    @PostMapping("/save")
    public String saveFlight(@ModelAttribute("flight") Flight flight) {
//        flight.setDepartureDateTime(conversionService.convert(flight.getDepartureDateTime(), java.time.LocalDateTime.class));
//        flight.setArrivalDateTime(conversionService.convert(flight.getArrivalDateTime().toString(), java.time.LocalDateTime.class));
        flightRepository.save(flight);
        return "redirect:/flights";
    }
    
    @GetMapping
    public String listFlights(Model model) {
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        return "flights/index";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditFlightForm(@PathVariable("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid flight ID"));
        model.addAttribute("flight", flight);
        return "flights/edit";
    }
    
    @PostMapping("/{id}/update")
    public String updateFlight(@PathVariable("id") Long id, @ModelAttribute("flight") Flight updatedFlight) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid flight ID"));
        // Mettre à jour les propriétés de l'objet flight
        flightRepository.save(flight);
        return "redirect:/flights";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteFlight(@PathVariable("id") Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid flight ID"));
        flightRepository.delete(flight);
        return "redirect:/flights";
    }
}
