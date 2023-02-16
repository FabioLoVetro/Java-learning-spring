package com.fabiolovetro.lil.learningspring.Business;

import com.fabiolovetro.lil.learningspring.data.Guest;
import com.fabiolovetro.lil.learningspring.data.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    public List<HotelGuests> getAllHotelGuests(){
        List<HotelGuests> hotelGuests = new ArrayList<>();
        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(g -> {
            HotelGuests hg = new HotelGuests();
            hg.setLastName(g.getLastName());
            hg.setFirstName(g.getFirstName());
            hg.seteMailAddress(g.geteMailAddress());
            hg.setPhoneNumber(g.getPhoneNumber());
            hotelGuests.add(hg);
        });
        return hotelGuests;
    }

    public void addGuest(Guest guest) {
        try {
            this.guestRepository.save(guest);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
