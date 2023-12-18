package be.oniryx.techlunchspock

import be.oniryx.techlunchspock.repositories.DemoRepository
import be.oniryx.techlunchspock.services.DemoService
import org.spockframework.spring.SpringBean
import spock.lang.Specification

class TechLunchAvanceTest extends Specification {

    DemoRepository demoRepository = Mock();

    @SpringBean
    DemoService demoService = new DemoService(demoRepository)

    def "Meme comportement pour 2 méthodes différentes"() {
        when: "On attend un crash"
        callMethod(demoService)

        then: "On a une runtime exception"
        RuntimeException e = thrown(RuntimeException.class)
        e.getMessage().contains(errorMessage)

        where: "Méthode à appeler" // /!\
        errorMessage | callMethod
        "cra"        | { args -> args.genericReturnMethodBoolean(true)}
        "sh"         | { args -> args.genericReturnMethodString(true)}
    }
}
