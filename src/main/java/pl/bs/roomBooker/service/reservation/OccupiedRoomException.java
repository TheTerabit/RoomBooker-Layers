package pl.bs.roomBooker.service.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The conference room is already occupied")
public class OccupiedRoomException extends Exception {
}
