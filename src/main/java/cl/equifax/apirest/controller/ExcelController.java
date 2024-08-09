package cl.equifax.apirest.controller;

import cl.equifax.apirest.dto.PersonDTO;
import cl.equifax.apirest.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService excelService;

    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            List<PersonDTO> processedPersons = excelService.processExcelFile(file);
            return ResponseEntity.ok(processedPersons);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> persons = excelService.getAllPersons();
        return ResponseEntity.ok(persons);
    }
}
