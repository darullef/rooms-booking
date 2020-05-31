package com.darullef.roomsbooking.dao;

import com.darullef.roomsbooking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDao extends CrudRepository<Booking, Long> {
    List<Booking> findAll();
    Optional<Booking> findById(Long id);
}
