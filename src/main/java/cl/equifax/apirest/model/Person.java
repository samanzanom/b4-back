package cl.equifax.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personas")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "rut", length = 20, nullable = false)
    private String rut;

    @Column(name = "campo1", length = 100)
    private String field1;

    @Column(name = "campo2", length = 100)
    private String field2;
}