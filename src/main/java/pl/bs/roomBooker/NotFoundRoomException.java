package pl.bs.roomBooker.domain.room;

public class NotFoundRoomException extends RuntimeException {

    public NotFoundRoomException(String message) {
        super(message);
    }
}
