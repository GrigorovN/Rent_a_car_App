package com.example.rentacarproject.repository;

import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE date_in between ?1 and ?2 AND date_out between ?1 and ?2")
    Optional<Set<Reservation>> getReservationsByIntervalWithNative(String start, String end);

    Optional<Set<Reservation>> findByUser(User user);

    Optional<Set<Reservation>> findByCar(Car car);
}
