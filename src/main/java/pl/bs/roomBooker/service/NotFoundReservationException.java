package pl.bs.roomBooker.service;

public class NotFoundReservationException extends RuntimeException {

    public NotFoundReservationException(String message) {
        super(message);
    }
}
