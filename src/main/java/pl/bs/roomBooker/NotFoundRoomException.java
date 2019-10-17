package pl.bs.roomBooker;

public class NotFoundRoomException extends RuntimeException {

    public NotFoundRoomException(String message) {
        super(message);
    }
}
