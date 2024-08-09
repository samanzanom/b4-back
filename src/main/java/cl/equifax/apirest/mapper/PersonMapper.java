package cl.equifax.apirest.mapper;

import cl.equifax.apirest.dto.PersonDTO;
import cl.equifax.apirest.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonDTO toDto(Person person) {
        if (person == null) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setRut(person.getRut());
        personDTO.setField1(person.getField1());
        personDTO.setField2(person.getField2());
        return personDTO;
    }

    public Person toEntity(PersonDTO personDTO) {
        if (personDTO == null) {
            return null;
        }

        Person person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setRut(personDTO.getRut());
        person.setField1(personDTO.getField1());
        person.setField2(personDTO.getField2());
        return person;
    }
}