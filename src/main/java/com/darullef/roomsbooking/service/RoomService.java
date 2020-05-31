package com.darullef.roomsbooking.service;


import com.darullef.roomsbooking.model.Room;
import com.darullef.roomsbooking.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;

    public void createRoom(Room room) {
        roomDao.save(room);
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(roomDao.findAll());
    }

    public Room getRoomById(Long id) {
        return roomDao.findById(id).get();
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
