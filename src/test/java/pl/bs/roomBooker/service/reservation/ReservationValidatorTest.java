package pl.bs.roomBooker.service.reservation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.reservation.Topic;
import pl.bs.roomBooker.models.room.Room;
import pl.bs.roomBooker.models.user.User;
import pl.bs.roomBooker.models.user.UserPassword;
import pl.bs.roomBooker.repository.reservation.ReservationRepository;
import pl.bs.roomBooker.repository.room.RoomRepository;
import pl.bs.roomBooker.repository.user.UserRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservationValidatorTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository = mock(RoomRepository.class, Mockito.RETURNS_DEEP_STUBS);

    @Mock
    private UserRepository userRepository = mock(UserRepository.class, Mockito.RETURNS_DEEP_STUBS);

    @Mock
    private ReservationValidator reservationValidator = new ReservationValidator(roomRepository, userRepository, reservationRepository);

    private User user;
    private Room room;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }

    @BeforeEach
    private void setupUser() {
        user = new User();
        user.setId(0L);
        user.setUsername("maluch126p");
        user.setName("Pawel");
        user.setSurname("Korbaczynski");
        user.setUserPassword(new UserPassword(0L,"password1"));
    }

    @BeforeEach
    private void setupRoom() {
        Reservation reservation = new Reservation();
        reservation.setUsername("maluch126p");
        reservation.setRoomId(0L);
        reservation.setTopic(new Topic("Projekt"));
        reservation.setReservationId(0L);
        reservation.setStart(ZonedDateTime.parse("2011-12-03T10:15:30+01:00"));
        reservation.setEnd(ZonedDateTime.parse("2011-12-03T12:15:30+01:00"));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        room = new Room();
        room.setName("Madagascar");
        room.setReservations(reservations);
        room.setRoomId(0L);
    }

    @Test
    public void validate_GoodReservationRequest_ShouldBeOk() {
        //given
        ReservationMsg reservationMsg = new ReservationMsg("maluch126p",
                "password1",
                0L,
                "Inzynierka",
                ZonedDateTime.parse("2011-12-03T12:15:30+01:00"),
                ZonedDateTime.parse("2011-12-03T14:15:30+01:00"));
        //when
        when(userRepository.findByUsername(reservationMsg.getUsername()))
                .thenReturn(java.util.Optional.of(user));
        when(roomRepository.findById(reservationMsg.getRoomId()))
                .thenReturn(java.util.Optional.of(room));
        //then
        assertDoesNotThrow(() -> reservationValidator.validate(reservationMsg));
    }

    @Test
    public void validate_StartBeforeEnd_WrongMeetingTimeException() {
        //given
        ReservationMsg reservationMsg = new ReservationMsg("maluch126p",
                "password1",
                0L,
                "Inzynierka",
                ZonedDateTime.parse("2011-12-03T16:15:30+01:00"),
                ZonedDateTime.parse("2011-12-03T15:15:30+01:00"));
        //when
        when(userRepository.findByUsername(reservationMsg.getUsername()))
                .thenReturn(java.util.Optional.of(user));
        when(roomRepository.findById(reservationMsg.getRoomId()))
                .thenReturn(java.util.Optional.of(room));
        //then
        Exception exception = assertThrows(WrongMeetingTimeException.class, () -> reservationValidator.validate(reservationMsg));
        assertEquals("End of the meeting must be after its start.", exception.getMessage());
    }

    @Test
    public void validate_TooShortReservation_WrongMeetingTimeException() {
        //given
        ReservationMsg reservationMsg = new ReservationMsg("maluch126p",
                "password1",
                0L,
                "Inzynierka",
                ZonedDateTime.parse("2011-12-03T16:00:00+01:00"),
                ZonedDateTime.parse("2011-12-03T16:14:00+01:00"));
        //when
        when(userRepository.findByUsername(reservationMsg.getUsername()))
                .thenReturn(java.util.Optional.of(user));
        when(roomRepository.findById(reservationMsg.getRoomId()))
                .thenReturn(java.util.Optional.of(room));
        //then
        Exception exception = assertThrows(WrongMeetingTimeException.class, () -> reservationValidator.validate(reservationMsg));
        assertEquals("Duration of the meeting must be 15 - 120 minutes.", exception.getMessage());
    }

    @Test
    public void validate_TooLongReservation_WrongMeetingTimeException() {
        //given
        ReservationMsg reservationMsg = new ReservationMsg("maluch126p",
                "password1",
                0L,
                "Inzynierka",
                ZonedDateTime.parse("2011-12-03T16:00:00+01:00"),
                ZonedDateTime.parse("2011-12-03T18:01:00+01:00"));
        //when
        when(userRepository.findByUsername(reservationMsg.getUsername()))
                .thenReturn(java.util.Optional.of(user));
        when(roomRepository.findById(reservationMsg.getRoomId()))
                .thenReturn(java.util.Optional.of(room));
        //then
        Exception exception = assertThrows(WrongMeetingTimeException.class, () -> reservationValidator.validate(reservationMsg));
        assertEquals("Duration of the meeting must be 15 - 120 minutes.", exception.getMessage());
    }

    @Test
    public void validate_ConflictReservationRequest_OccupiedRoomException() {
        //given
        ReservationMsg reservationMsg = new ReservationMsg("maluch126p",
                "password1",
                0L,
                "Inzynierka",
                ZonedDateTime.parse("2011-12-03T11:00:00+01:00"),
                ZonedDateTime.parse("2011-12-03T13:00:00+01:00"));
        //when
        when(userRepository.findByUsername(reservationMsg.getUsername()))
                .thenReturn(java.util.Optional.of(user));
        when(roomRepository.findById(reservationMsg.getRoomId()))
                .thenReturn(java.util.Optional.of(room));
        //then
        assertThrows(OccupiedRoomException.class, () -> reservationValidator.validate(reservationMsg));
    }

}