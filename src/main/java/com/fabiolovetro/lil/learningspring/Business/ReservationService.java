package com.fabiolovetro.lil.learningspring.Business;

import com.fabiolovetro.lil.learningspring.data.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository,
                              GuestRepository guestRepository,
                              ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<HotelReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, HotelReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            HotelReservation hotelReservation = new HotelReservation();
            hotelReservation.setRoomId(room.getId());
            hotelReservation.setRoomName(room.getName());
            hotelReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), hotelReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            HotelReservation hotelReservation = roomReservationMap.get(reservation.getRoomId());
            hotelReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            hotelReservation.setFirstName(guest.getFirstName());
            hotelReservation.setLastName(guest.getLastName());
            hotelReservation.setGuestId(guest.getGuestId());
        });
        List<HotelReservation> hotelReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            hotelReservations.add(roomReservationMap.get(id));
        }
        hotelReservations.sort(new Comparator<HotelReservation>() {
            @Override
            public int compare(HotelReservation o1, HotelReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return hotelReservations;
    }
}
