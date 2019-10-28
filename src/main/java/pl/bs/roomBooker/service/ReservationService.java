package pl.bs.roomBooker.service;

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
    private ReservationValidator reservationValidator;

    public ReservationService(ReservationRepository reservationRepository, TopicRepository topicRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.topicRepository = topicRepository;
        this.reservationValidator = new ReservationValidator(roomRepository, userRepository, reservationRepository);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundReservationException(String.format("Reservation %s does not exist", id)));
    }

    public void create(ReservationMsg reservationMsg) throws Exception {

        this.reservationValidator.validate(reservationMsg);

        Topic topic = topicRepository.findByTopicName(reservationMsg.getTopic());
        if(topic == null){
            topic = new Topic(reservationMsg.getTopic());
        }

        topicRepository.save(topic);


        Reservation reservation = new Reservation(reservationMsg.getUsername(),
                                        reservationMsg.getRoomId(),
                                        reservationMsg.getStart(),
                                        reservationMsg.getEnd());

        reservation.setTopic(topic);

        reservationRepository.save(reservation);

    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public void update(Long id, ReservationMsg reservationMsg) throws Exception {
        reservationValidator.validateUpdate(id, reservationMsg);
        reservationRepository.update(id, reservationMsg.getTopic(), reservationMsg.getRoomId(), reservationMsg.getStart(), reservationMsg.getEnd());
    }
}
