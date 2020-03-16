package pl.bs.roomBooker.service.reservation;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.user.User;
import pl.bs.roomBooker.repository.reservation.ReservationRepository;
import pl.bs.roomBooker.repository.room.RoomRepository;
import pl.bs.roomBooker.repository.user.UserRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationValidator {

    private ReservationMsg reservationMsg;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReservationValidator(RoomRepository roomRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    public void validate(ReservationMsg reservationMsg) throws Exception {
        setReservationMsg(reservationMsg);
        authenticateUser();
        checkIfStartBeforeEnd();
        checkReservationLength();
        checkIfRoomIsFree();
    }

    private void setReservationMsg(ReservationMsg reservationMsg) {
        this.reservationMsg = reservationMsg;
    }

    private void authenticateUser() throws AuthenticationFailureException {
        if(!this.reservationMsg.getPassword().equals(this.userRepository.findByUsername(reservationMsg.getUsername()).get().getUserPassword().getPassword()))
            throw new AuthenticationFailureException();
    }

    private void checkIfStartBeforeEnd() throws WrongMeetingTimeException {
        if(reservationMsg.getStart().isAfter(reservationMsg.getEnd()))
            throw new WrongMeetingTimeException("End of the meeting must be after its start.");
    }

    private void checkReservationLength() throws WrongMeetingTimeException {
        if(!this.checkIfMeetingLengthIsOk(reservationMsg.getStart(), reservationMsg.getEnd()))
            throw new WrongMeetingTimeException("Duration of the meeting must be 15 - 120 minutes.");
    }

    private boolean checkIfMeetingLengthIsOk(ZonedDateTime start, ZonedDateTime end) {
        long eventLength = end.toInstant().toEpochMilli() - start.toInstant().toEpochMilli();
        if((eventLength >= TimeUnit.MINUTES.toMillis(15))&&(eventLength <= TimeUnit.MINUTES.toMillis(120)))
            return true;
        else
            return false;
    }

    private void checkIfRoomIsFree() throws OccupiedRoomException {
        if(!checkIfFree(reservationMsg.getStart(), reservationMsg.getEnd(), roomRepository.findById(reservationMsg.getRoomId()).get().getReservations()))
            throw new OccupiedRoomException();
    }

    private boolean checkIfFree(ZonedDateTime start, ZonedDateTime end, List<Reservation> allReservations){//stream
        for(Reservation reservation : allReservations){
            if(!((!start.isBefore(reservation.getEnd()))||(!end.isAfter(reservation.getStart()))))
                return false;
        }
        return true;
    }

    public void validateUpdate(Long id, ReservationMsg reservationMsg) throws Exception {
        this.setReservationMsg(reservationMsg);
        this.authenticateUser();
        this.checkReservationOwner(id);
        this.checkIfStartBeforeEnd();
        this.checkReservationLength();
        this.checkIfRoomIsFree(id);

    }

    private void checkReservationOwner(Long id) throws WrongUserException {
        if(!reservationRepository.findById(id).get().getUsername().equals(reservationMsg.getUsername()))
            throw new WrongUserException();
    }

    private void checkIfRoomIsFree(Long id) throws OccupiedRoomException {
        List<Reservation> reservations = new ArrayList<>(roomRepository.findById(reservationMsg.getRoomId()).get().getReservations());
        Reservation currentReservation = reservations.stream()
                .filter(reservation -> id.equals(reservation.getReservationId()))
                .findAny()
                .orElse(null);
        reservations.remove(currentReservation);

        if(!checkIfFree(reservationMsg.getStart(), reservationMsg.getEnd(), reservations))
            throw new OccupiedRoomException();
    }

    public void validateDelete(Long id, ReservationMsg reservationMsg) throws Exception {
        this.setReservationMsg(reservationMsg);
        this.authenticateUser();
        this.checkReservationOwner(id);
    }

}

