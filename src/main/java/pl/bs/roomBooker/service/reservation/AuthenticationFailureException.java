package pl.bs.roomBooker.service.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Wrong username or password")
public class AuthenticationFailureException extends Exception {
}
