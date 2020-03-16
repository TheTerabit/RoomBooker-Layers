package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.*;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.room.Room;
import pl.bs.roomBooker.service.reservation.ReservationService;
import pl.bs.roomBooker.service.room.RoomService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationEndpoint {

    private final ReservationService reservationService;
    private final RoomService roomService;

    public ReservationEndpoint(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @PostMapping
    public void create(@RequestBody ReservationMsg reservationMsg) throws Exception {
        reservationService.create(reservationMsg);
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable("id") Long id) {
        return reservationService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id, @RequestBody ReservationMsg reservationMsg) throws Exception {
        reservationService.delete(id, reservationMsg);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody ReservationMsg reservationMsg) throws Exception {
        reservationService.update(id, reservationMsg);
    }

    @GetMapping("/new")
    public List<Room> getAllRooms(){
        return roomService.getAll();
    }

}
