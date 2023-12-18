package be.oniryx.techlunchspock

import be.oniryx.techlunchspock.repositories.DemoRepository
import be.oniryx.techlunchspock.services.DemoService
import org.spockframework.spring.SpringBean
import spock.lang.Specification

class TechLunchProdTests extends Specification{

    DemoRepository demoRepository = Mock();

    @SpringBean
    DemoService demoService = new DemoService(demoRepository)

    def "Testing d'exception"(){
        when: "Methode qui va crash"
        demoService.genericReturnMethodBoolean(true)

        then: "On a une runtime exception"
        RuntimeException e = thrown(RuntimeException.class)
        e.getMessage().contains("crash")
    }
}
