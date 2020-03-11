package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bs.roomBooker.service.InsertionService;

@RestController("/insert")
public class InsertionEndpoint {

    private final InsertionService insertionService;

    public InsertionEndpoint(InsertionService insertionService) {
        this.insertionService = insertionService;
    }

    @GetMapping("/insert")
    public void initData(){
        this.insertionService.initData();
    }
}
//