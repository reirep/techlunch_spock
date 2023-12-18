package be.oniryx.techlunchspock.services;

import be.oniryx.techlunchspock.models.DemoModel;
import be.oniryx.techlunchspock.repositories.DemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public List<DemoModel> findAll(){
        return demoRepository.findAll();
    }

    public DemoModel findById(Long id){
         return demoRepository.findById(id).orElse(null);
    }

    public void saveAll(ArrayList<DemoModel> models){
        demoRepository.saveAll(models);
    }
}
