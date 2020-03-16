package pl.bs.roomBooker.service.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongMeetingTimeException extends Exception {

    public WrongMeetingTimeException(String message) {
        super(message);
    }

}
