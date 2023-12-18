package be.oniryx.techlunchspock.controllers;

import be.oniryx.techlunchspock.models.DemoModel;
import be.oniryx.techlunchspock.services.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    public List<DemoModel> findAll(){
        return demoService.findAll();
    }
}
