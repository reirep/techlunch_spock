package be.oniryx.techlunchspock.repositories;

import be.oniryx.techlunchspock.models.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository  extends JpaRepository<DemoModel, Long> {
}
