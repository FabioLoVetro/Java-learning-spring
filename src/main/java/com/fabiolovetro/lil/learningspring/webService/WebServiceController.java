package com.fabiolovetro.lil.learningspring.webService;

import com.fabiolovetro.lil.learningspring.Business.*;
import com.fabiolovetro.lil.learningspring.data.Guest;
import com.fabiolovetro.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final GuestService guestsService;
    private final RoomService roomService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService, GuestService guestsService, RoomService roomService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.guestsService = guestsService;
        this.roomService = roomService;
    }
    @RequestMapping(path = "/reservation", method = RequestMethod.GET)
    public List<HotelReservation> getAllReservations(
            @RequestParam(value="data",required = false)String dataString){
        Date date = this.dateUtils.createDateFromDateString(dataString);
        return this.reservationService.getRoomReservationsForDate(date);
    }
    @GetMapping(path = "/room")
    public List getAllRooms(){
        return this.roomService.getAllRooms();
    }
    @GetMapping(path = "/guest")
    public List<HotelGuests> getAllGuest(){
        return this.guestsService.getAllHotelGuests();
    }
    @PostMapping(path = "/guest")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest){
        this.guestsService.addGuest(guest);
    }
}
