package be.oniryx.techlunchspock

import be.oniryx.techlunchspock.models.DemoModel
import be.oniryx.techlunchspock.repositories.DemoRepository
import be.oniryx.techlunchspock.services.DemoService
import be.oniryx.techlunchspock.services.QuickMathService
import org.spockframework.spring.SpringBean
import spock.lang.Specification

import javax.swing.text.html.Option

class TechLunchAvanceTest extends Specification {

    DemoRepository demoRepository = Mock();

    QuickMathService quickMathService = Mock()

    @SpringBean
    DemoService demoService = new DemoService(demoRepository, quickMathService)

    def "Utilisation du mot-clef assert et assert implicite"() {
        expect: "On regarde si c'est sensé etre empty"
        if (expectedResult.isEmpty()){
            assert demoService.genericOptionalMethod(input).isEmpty()
        } else {
            assert demoService.genericOptionalMethod(input) == expectedResult
        }

        where: " inputs"
        input | expectedResult
        true  | Optional.empty()
        false | Optional.of("kirk")
    }

    def "Matching d'arguments par fonction lambda de match"() {
        when: "On fait le test"
        demoService.saveAll([new DemoModel(0, "name")])

        then: "On vérifie"
        1 * demoRepository.saveAll({
            ArrayList<DemoModel> list -> !list.isEmpty() && list.get(0).id == 0
        })
    }

    def "Test avec datatables de fonction lambda anonymes"() {
        when: "On attend un crash"
        callMethod(demoService)

        then: "On a une runtime exception"
        RuntimeException e = thrown(RuntimeException.class)
        e.getMessage().contains(errorMessage)

        where: "Méthode à appeler"
        errorMessage | callMethod
        "cra"        | { args -> args.genericReturnMethodBoolean(true) }
        "sh"         | { args -> args.genericReturnMethodString(true) }
    }

    def "Vérifier qu'on objet n'a eu aucun appel sur toutes ses méthodes"() {
        when: "appelle d'une méthode"
        demoService.genericReturnMethodBoolean(false)
        then: "Quick math n'est pas appelé"
        0 * quickMathService._

    }
}
