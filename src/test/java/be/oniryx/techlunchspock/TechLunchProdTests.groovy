package be.oniryx.techlunchspock

import be.oniryx.techlunchspock.controllers.DemoController
import be.oniryx.techlunchspock.models.DemoModel
import be.oniryx.techlunchspock.repositories.DemoRepository
import be.oniryx.techlunchspock.services.AdvancedMathService
import be.oniryx.techlunchspock.services.DemoService
import be.oniryx.techlunchspock.services.QuickMathService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("Application Specification")
@Narrative("Specification which beans are expected")

@SpringBootTest
class TechLunchProdTests extends Specification{

    @Autowired
    private DemoController demoController

    @Autowired
    private AdvancedMathService advancedMathService

    @Autowired
    private QuickMathService quickMathService


    DemoRepository demoRepository = Mock()

    @SpringBean
    DemoService demoService = new DemoService(demoRepository, quickMathService)

    def "Vérifier que le contexte est load correctement"() {
        expect: "demo controller"
        demoController
        and: " Permet de séparer les sous test en cas de bug"
        advancedMathService
        and: "ici pareil"
        quickMathService
    }

    def "Testing d'exception"(){
        when: "Methode qui va crash"
        demoService.genericReturnMethodBoolean(true)

        then: "On a une runtime exception"
        RuntimeException e = thrown(RuntimeException.class)
        e.getMessage().contains("crash")
    }

    def "Casser le private d'un champs"() {
        setup:"On écrase la valeur du champ private"
        demoService.definitelyAPrivateList = [new DemoModel()] // crée un array list
        expect: " On vérifie"
        demoService.getDefinitelyAPrivateList().size() == 1
    }
}
