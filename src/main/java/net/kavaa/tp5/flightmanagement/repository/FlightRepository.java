/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
*/
package net.kavaa.tp5.flightmanagement.repository;

import net.kavaa.tp5.flightmanagement.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Admin Inclusiv 2
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {}
