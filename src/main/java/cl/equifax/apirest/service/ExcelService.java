package cl.equifax.apirest.service;

import cl.equifax.apirest.dto.PersonDTO;
import cl.equifax.apirest.mapper.PersonMapper;
import cl.equifax.apirest.model.Person;
import cl.equifax.apirest.repository.PersonRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public ExcelService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> processExcelFile(MultipartFile file) throws IOException {
        List<Person> persons = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Person person = new Person();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            person.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            person.setRut(currentCell.getStringCellValue());
                            break;
                        case 2:
                            person.setField1(currentCell.getStringCellValue());
                            break;
                        case 3:
                            person.setField2(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                persons.add(person);
            }
        }

        List<Person> savedPersons = personRepository.saveAll(persons);
        return savedPersons.stream()
                .map(personMapper::toDto)
                .toList();
    }

    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(personMapper::toDto)
                .toList();
    }
}