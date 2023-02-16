package com.fabiolovetro.lil.learningspring.Business;

import com.fabiolovetro.lil.learningspring.data.Room;
import com.fabiolovetro.lil.learningspring.data.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<HotelRooms> getAllRooms(){
        List<HotelRooms> list = new ArrayList<>();
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(r -> {
            HotelRooms hr = new HotelRooms();
            hr.setId(r.getId());
            hr.setName(r.getName());
            hr.setRoomNumber(r.getRoomNumber());
            hr.setBedInfo(r.getBedInfo());
            list.add(hr);
        });
        return list;
    }
}
