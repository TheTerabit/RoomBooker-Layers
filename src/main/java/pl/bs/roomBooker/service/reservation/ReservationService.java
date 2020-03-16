package pl.bs.roomBooker.service.reservation;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.reservation.Topic;
import pl.bs.roomBooker.repository.reservation.ReservationRepository;
import pl.bs.roomBooker.repository.reservation.TopicRepository;
import pl.bs.roomBooker.repository.room.RoomRepository;
import pl.bs.roomBooker.repository.user.UserRepository;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TopicRepository topicRepository;
    private final ReservationValidator reservationValidator;

    public ReservationService(ReservationRepository reservationRepository,
                              TopicRepository topicRepository,
                              RoomRepository roomRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.topicRepository = topicRepository;
        this.reservationValidator = new ReservationValidator(roomRepository, userRepository, reservationRepository);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(String.format("Reservation %s does not exist", id)));
    }

    public void create(ReservationMsg reservationMsg) throws Exception {
        reservationValidator.validate(reservationMsg);
        Topic topic = createTopic(reservationMsg);
        Reservation reservation = createReservation(reservationMsg, topic);
        reservationRepository.save(reservation);
    }

    private Topic createTopic(ReservationMsg reservationMsg){
        Topic topic = topicRepository.findByTopicName(reservationMsg.getTopic());
        if(topic == null){
            topic = new Topic(reservationMsg.getTopic());
        }
        topicRepository.save(topic);
        return topic;
    }

    private Reservation createReservation(ReservationMsg reservationMsg, Topic topic) {
        Reservation reservation = new Reservation(reservationMsg.getUsername(),
                reservationMsg.getRoomId(),
                reservationMsg.getStart(),
                reservationMsg.getEnd());
        reservation.setTopic(topic);
        return reservation;
    }

    public void delete(Long id, ReservationMsg reservationMsg) throws Exception {
        reservationValidator.validateDelete(id, reservationMsg);
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public void update(Long id, ReservationMsg reservationMsg) throws Exception {
        reservationValidator.validateUpdate(id, reservationMsg);
        reservationRepository.update(id, createTopic(reservationMsg), reservationMsg.getRoomId(), reservationMsg.getStart(), reservationMsg.getEnd());
    }
}
