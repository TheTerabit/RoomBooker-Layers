package pl.bs.roomBooker.service;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.repository.room.RoomRepository;
import pl.bs.roomBooker.repository.user.UserRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationValidator {

    private ReservationMsg reservationMsg;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public ReservationValidator(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public void setReservationMsg(ReservationMsg reservationMsg) {
        this.reservationMsg = reservationMsg;
    }

    public void checkIfStartBeforeEnd() throws Exception {
        if(reservationMsg.getStart().isAfter(reservationMsg.getEnd()))
            throw new Exception("End of the meeting must be after its start.");
    }

    public void checkReservationLength() throws Exception {
        if(!this.checkEventLength(reservationMsg.getStart(), reservationMsg.getEnd()))
            throw new Exception("Duration of the meeting must be 15 - 120 minutes.");
    }

    public void checkIfRoomIsFree() throws Exception {
        if(!checkIfFree(reservationMsg.getStart(), reservationMsg.getEnd(), roomRepository.findById(reservationMsg.getRoomId()).get().getReservations()))
            throw new Exception("The conference room is already occupied.");
    }

    private boolean checkIfFree(ZonedDateTime start, ZonedDateTime end, List<Reservation> allReservations){//stream
        for(Reservation reservation : allReservations){
            if(!((!start.isBefore(reservation.getEnd()))||(!end.isAfter(reservation.getStart()))))
                return false;
        }
        return true;
    }

    private boolean checkEventLength(ZonedDateTime start, ZonedDateTime end){
        long eventLength=end.toInstant().toEpochMilli()-start.toInstant().toEpochMilli();
        if((eventLength >= TimeUnit.MINUTES.toMillis(15))&&(eventLength <= TimeUnit.MINUTES.toMillis(120)))
            return true;
        else
            return false;
    }

    public void authenticateUser() throws Exception {
        if(!this.reservationMsg.getPassword().equals(this.userRepository.findById(reservationMsg.getUserId()).get().getUserPassword().getPassword()))
            throw new Exception("Wrong username or password.");
    }
}

