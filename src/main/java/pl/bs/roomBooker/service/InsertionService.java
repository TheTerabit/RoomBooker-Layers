package pl.bs.roomBooker.service;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.RoomMsg;
import pl.bs.roomBooker.controllers.msg.UserMsg;
import pl.bs.roomBooker.models.user.Company;
import pl.bs.roomBooker.repository.user.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

@Service
public class InsertionService {

    private final RoomService roomService;
    private final UserService userService;
    private final CompanyRepository companyRepository;

    public InsertionService(RoomService roomService, UserService userService, CompanyRepository companyRepository) {
        this.roomService = roomService;
        this.userService = userService;
        this.companyRepository = companyRepository;
    }


    public void initData(){
        this.deleteAll();
        this.insertUsers();
        this.insertRooms();
        this.insertCopanies();

    }

    private void deleteAll() {
        EntityManagerFactory factory = null;
        EntityManager entityManager = null;
        factory = Persistence.createEntityManagerFactory("room_booker");
        entityManager = factory.createEntityManager();
        StoredProcedureQuery findByYearProcedure = entityManager.createNamedStoredProcedureQuery("filldb");
        findByYearProcedure.execute();
    }

    private void insertCopanies() {
        this.companyRepository.save(new Company("Atos IT Services sp. z o.o."));
        this.companyRepository.save(new Company("Comarch SA"));
    }

    private void insertRooms() {
        this.roomService.create(new RoomMsg("Haiti",
                                            2,
                                            1,
                                            "A"));

        this.roomService.create(new RoomMsg("Madagascar",
                4,
                2,
                "B"));

        this.roomService.create(new RoomMsg("Cuba",
                8,
                3,
                "A"));
    }

    private void insertUsers(){
        this.userService.create(new UserMsg("maluch126p",
                "Paweł",
                "Korbaczyński",
                "password1",
                "Starołęka",
                "1/1",
                "Poznań",
                "Polska",
                "pawel.korbaczynski@student.put.poznan.pl",
                "111111111",
                "Korbex sp. z o.o.",
                "CEO"));

        this.userService.create(new UserMsg("krazek_hanoi",
                "Bartol",
                "Omeo",
                "password2",
                "Rataje",
                "2/2",
                "Bydgoszcz",
                "Polska",
                "bartol.omeo@student.put.poznan.pl",
                "222222222",
                "United States Playing Cards Company",
                "tester"));

        this.userService.create(new UserMsg("hamsterMaster",
                "Marek",
                "Grabowski",
                "password3",
                "Greenstreet",
                "3/3",
                "New York",
                "United States",
                "marek.grabowski@student.put.poznan.pl",
                "333333333",
                "Best Sushi",
                "Cashier"));
    }

}
