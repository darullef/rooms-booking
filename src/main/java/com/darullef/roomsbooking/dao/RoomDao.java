package com.darullef.roomsbooking.dao;

import com.darullef.roomsbooking.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomDao extends CrudRepository<Room, Long> {
    List<Room> findAll();
    Optional<Room> findById(Long id);
}
