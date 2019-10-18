package pl.bs.roomBooker.service;

public class NotFoundRoomException extends RuntimeException {

    public NotFoundRoomException(String message) {
        super(message);
    }
}
