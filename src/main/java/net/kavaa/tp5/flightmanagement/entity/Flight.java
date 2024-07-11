/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package net.kavaa.tp5.flightmanagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin Inclusiv 2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "flightNumber", nullable = false)
    private String flightNumber;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm",iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "departureDateTime", nullable = false)
    private LocalDateTime departureDateTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm",iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "arrivalDateTime", nullable = false)
    private LocalDateTime arrivalDateTime;
    
    @Column(name = "departureCity", nullable = false)
    private String departureCity;
    
    @Column(name = "arrivalCity", nullable = false)
    private String arrivalCity;
    
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();
    
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();
    
}
