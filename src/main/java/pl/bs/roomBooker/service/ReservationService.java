package pl.bs.roomBooker.service;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.reservation.Topic;
import pl.bs.roomBooker.repository.reservation.ReservationRepository;
import pl.bs.roomBooker.repository.reservation.TopicRepository;
import pl.bs.roomBooker.repository.room.RoomRepository;
import pl.bs.roomBooker.repository.user.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
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

        Topic topic = this.createTopic(reservationMsg);


        Reservation reservation = new Reservation(reservationMsg.getUsername(),
                                        reservationMsg.getRoomId(),
                                        reservationMsg.getStart(),
                                        reservationMsg.getEnd());

        reservation.setTopic(topic);

        reservationRepository.save(reservation);

    }
    private Topic createTopic(ReservationMsg reservationMsg){
        Topic topic = topicRepository.findByTopicName(reservationMsg.getTopic());
        if(topic == null){
            /*EntityManagerFactory factory = null;
            EntityManager entityManager = null;
            factory = Persistence.createEntityManagerFactory("jpa-db");
            entityManager = factory.createEntityManager();
            StoredProcedureQuery findByYearProcedure =
                    entityManager.createNamedStoredProcedureQuery("findByYearProcedure");

            StoredProcedureQuery storedProcedure =
                    findByYearProcedure.setParameter("p_year", 2015);

            */
            topic = new Topic(reservationMsg.getTopic());
        }

        topicRepository.save(topic);

        return topic;
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
        reservationRepository.update(id, this.createTopic(reservationMsg), reservationMsg.getRoomId(), reservationMsg.getStart(), reservationMsg.getEnd());
    }
}
