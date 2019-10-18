package pl.bs.roomBooker.service;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.reservation.Topic;
import pl.bs.roomBooker.repository.reservation.ReservationRepository;
import pl.bs.roomBooker.repository.reservation.TopicRepository;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TopicRepository topicRepository;

    public ReservationService(ReservationRepository reservationRepository, TopicRepository topicRepository) {
        this.reservationRepository = reservationRepository;
        this.topicRepository = topicRepository;
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundReservationException(String.format("User %s does not exist", id)));
    }

    public void create(ReservationMsg reservationMsg) {
        //if()
        Topic topic = topicRepository.findByTopicName(reservationMsg.getTopic());
        if(topic == null){
            topic = new Topic(reservationMsg.getTopic());
        }

        topicRepository.save(topic);


        Reservation reservation = new Reservation(reservationMsg.getUserId(),
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

}
