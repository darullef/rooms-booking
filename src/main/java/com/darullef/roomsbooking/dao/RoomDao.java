package com.darullef.roomsbooking.dao;

import com.darullef.roomsbooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomDao extends JpaRepository<Room, Long> {
    List<Room> findAll();
    Optional<Room> findById(Long id);
    List<Room> findAllByCapacityIsBetween(int n, int m);
    List<Room> findRoomsByHasProjectorIs(boolean x);
}
