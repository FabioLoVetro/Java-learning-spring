package com.fabiolovetro.lil.learningspring.web;

import com.fabiolovetro.lil.learningspring.Business.GuestService;
import com.fabiolovetro.lil.learningspring.Business.HotelGuests;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/guest")
public class GuestController {
    private final GuestService guestsService;

    public GuestController(GuestService guestsService) {
        this.guestsService = guestsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHotelGuests(Model model) {
        List<HotelGuests> hotelGuests = this.guestsService.getAllHotelGuests();
        model.addAttribute("hotelGuests",hotelGuests);
        return "hotel-guests";
    }
}
