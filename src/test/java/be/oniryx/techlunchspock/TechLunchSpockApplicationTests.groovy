package be.oniryx.techlunchspock

import be.oniryx.techlunchspock.models.DemoModel
import be.oniryx.techlunchspock.repositories.DemoRepository
import be.oniryx.techlunchspock.services.DemoService
import org.spockframework.spring.SpringBean
import spock.lang.Specification

//@SpringBootTest // Pas nécessaire si on veut lancer les test hors d'un contexte spring mais se lancera quand meme avec maven test
class TechLunchSpockApplicationTests extends Specification { // AJOUTER extend specification

    DemoRepository demoRepository = Mock();

    @SpringBean
    DemoService demoService = new DemoService(demoRepository)

    def "Nom du test"() {
        setup: "Configuration avant le test"
        // On s'attend a ce que findAll soit appelé une fois et on dis que la valeur de retour est un tableau vide
        1 * demoRepository.findAll() >> []
        // d'autre possibilité de nombres : 0, 1..3 (entre 1 et 3), 1.._ (au moins 1 call), etc ...

        when: "L'appel à effectuer"
        var returnValue = demoService.findAll()

        then: "Vérification post appel"
        returnValue.size() == 0
    }

    def "Une valeur de retour spécifique"() {
        setup: "Configuration avant le test"
        1 * demoRepository.findAll() >> [new DemoModel(1, "Kirk")] // on utilise la variable déclarée dans la data table

        when: "L'appel à effectuer"
        var returnValue = demoService.findAll()

        then: "Vérification post appel"
        returnValue[0].name == "Kirk"
    }

    def "Passer un paramètre spécifique à une fonction"(){
        setup: "Configuration avant le test"
        1 * demoRepository.findById( 1L) >> Optional.empty()

        when: "L'appel à effectuer"
        var returnValue = demoService.findById(1L)
        //var returnValue2 = demoService.findById(2L) // Sans valeur non spy

        then: "Vérification post appel"
        assert returnValue == null
    }

    def "Passer un paramètre typé à une fonction"(){
        setup: "Configuration avant le test"
        2 * demoRepository.findById( _ as Long) >> Optional.empty() // n'importe quoi qui est un long

        when: "L'appel à effectuer"
        var returnValue = demoService.findById(1L)
        var returnValue2 = demoService.findById(2L)

        then: "Vérification post appel"
        assert returnValue == null
        assert returnValue2 == null
    }

    def "Data table à 2 colonnes"(){
        setup: "Configuration avant le test"
        1 * demoRepository.findById( _ as Long) >> Optional.ofNullable(valeur)

        when: "L'appel à effectuer"
        var returnValue = demoService.findById(id)

        then: "Vérification post appel"
        assert returnValue == valeur

        where: "Data table à 2 colonnes"

        // Spock lancera un sub test par ligne de la data table avec les valeurs de la ligne pour chaque variable
        id | valeur
        1L | new DemoModel(1L, "model 1")
        2L | new DemoModel(2L, "model 2")
        3L | null
    }

    def "Verifier ce qui a été fournis en input à une méthode"() {
        setup: "Configuration avant le test"

        when: "L'appel à effectuer"
        demoService.saveAll([new DemoModel()]) // par défaut [] crée un ArrayList

        then: "Vérification post appel"
        1 * demoRepository.saveAll( _ as ArrayList) >> {args -> assert args[0].size() == 1} // Il faut bien qu'on récupère la valeur du premier argument

    }

    def "Verifier ce qui a été fournis en input à une méthode et fournir un output"() {
        setup: "Configuration avant le test"
        1 * demoRepository.findById(_ as Long) >> { args ->  // Pour fournir une valeur de retour selon la valeur en input, on utilise les closures
            assert args[0] == id // ici on recoit un tableau avec la valeur d'id à l'indice 0 et on vérifie que c'est correct
            Optional.ofNullable(valeur) // On donne la valeur à fournir en output
        }
        when: "L'appel à effectuer"
        var returnValue = demoService.findById(id)

        then: "Vérification post appel"
        assert returnValue == valeur // Les deux sont ok
        returnValue == valeur // Si pas dans une structure (if, etc....)

        where: "Data table à 2 colonnes"

        // Spock lancera un sub test par ligne de la data table avec les valeurs de la ligne pour chaque variable
        id | valeur
        1L | new DemoModel(1L, "model 1")
        2L | new DemoModel(2L, "model 2")
        3L | null

    }

}
