package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.*;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationEndpoint {

    private final ReservationService reservationService;

    public ReservationEndpoint(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAll() {
        return this.reservationService.getAll();
    }

    @PostMapping
    public void create(@RequestBody ReservationMsg reservationMsg) throws Exception {
        this.reservationService.create(reservationMsg);
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable("id") Long id) {
        return this.reservationService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.reservationService.delete(id);
    }

    //update

}
