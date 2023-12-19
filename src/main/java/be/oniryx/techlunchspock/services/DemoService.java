package be.oniryx.techlunchspock.services;

import be.oniryx.techlunchspock.models.DemoModel;
import be.oniryx.techlunchspock.repositories.DemoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    @Setter
    @Getter
    private List<DemoModel> definitelyAPrivateList;

    private final DemoRepository demoRepository;
    private final QuickMathService quickMathService;

    public DemoService(DemoRepository demoRepository, QuickMathService quickMathService) {
        this.demoRepository = demoRepository;
        this.quickMathService = quickMathService;
        this.definitelyAPrivateList = new ArrayList<>();
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

    public boolean genericReturnMethodBoolean(boolean doCrash){
        if(doCrash){
            throw new RuntimeException("crash");
        }
        return true;
    }

    public String genericReturnMethodString(boolean doCrash){
        if(doCrash){
            throw new RuntimeException("crash");
        }
        return "true";
    }

    public Optional<String> genericOptionalMethod(boolean isEmpty){
        return isEmpty ? Optional.empty() : Optional.of("kirk");
    }
}
