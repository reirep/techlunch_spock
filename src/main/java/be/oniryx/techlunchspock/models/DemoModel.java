package be.oniryx.techlunchspock.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "demo_model")
@AllArgsConstructor
@NoArgsConstructor
public class DemoModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
