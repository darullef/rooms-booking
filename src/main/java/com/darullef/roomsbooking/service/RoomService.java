package com.darullef.roomsbooking.service;

import com.darullef.roomsbooking.model.Booking;
import com.darullef.roomsbooking.model.Room;
import com.darullef.roomsbooking.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BookingService bookingService;

    public void createRoom(Room room) {
        roomDao.save(room);
    }

    public List<Room> getAllRooms() {
        if(roomDao.findAll().isEmpty()) {
            throw new NoSuchElementException();
        }
        else return roomDao.findAll();
    }

    public Room getRoomById(Long id) {
        return roomDao.findById(id).get();
    }

    public List<Room> getAllBookedRooms() {
        List<Room> rooms = new ArrayList<>();
        for(Booking b : bookingService.returnAllBookings()){
            rooms.addAll(b.getRooms());
        }
        Set<Room> roomSet = new HashSet<>(rooms);
        rooms.clear();
        rooms.addAll(roomSet);

        if(rooms.isEmpty()) {
            throw new NoSuchElementException();
        }
        else return rooms;
    }

    public List<Room> getAllFreeRoomsInTime(Timestamp start, Timestamp end) {
        List<Room> allRooms = roomDao.findAll();
        List<Room> bookedRooms = new ArrayList<>();
        for(Booking b : bookingService.returnAllBookingsInTime(start, end)) {
            bookedRooms.addAll(b.getRooms());
        }
        for(Room bRoom : bookedRooms) {
            allRooms.removeIf(room -> room.equals(bRoom));
        }

        if(allRooms.isEmpty()) {
            throw new NoSuchElementException();
        }
        else return allRooms;
    }

    public List<Room> getAllRoomsWhereCapacityBetween(int n, int m) {
        if(roomDao.findAllByCapacityIsBetween(n, m).isEmpty()) {
            throw new NoSuchElementException();
        }
        else return roomDao.findAllByCapacityIsBetween(n, m);
    }

    public List<Room> getAllRoomsWithProjector(boolean x) {
        if(roomDao.findRoomsByHasProjectorIs(x).isEmpty()) {
            throw new NoSuchElementException();
        }
        else return roomDao.findRoomsByHasProjectorIs(x);
    }

    public void updateRoom(Room room, Room newRoom) {
        room.setNumber(newRoom.getNumber());
        room.setCapacity(newRoom.getCapacity());
        room.setHasProjector(newRoom.isHasProjector());
        roomDao.save(room);
    }

    public void deleteRoom(Long id) {
        roomDao.delete(roomDao.findById(id).get());
    }
}
