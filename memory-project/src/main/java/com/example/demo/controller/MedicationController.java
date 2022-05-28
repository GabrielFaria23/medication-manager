package com.example.demo.controller;

import com.example.demo.dto.MedicationDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.MedicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@Api(value = "Endpoint de Medicamentos", description = "Endpoint utilizado para fazer operações na entidade de Medicamentos", tags = "Endpoint de Medicamentos")
@RestController
@RequestMapping("/v1/medication")
public class MedicationController {

    @Autowired
    private MedicationService service;

    @PostMapping
    @ApiOperation(value = "Registra um Medicamento")
    public ResponseEntity<MedicationDto> save(@RequestBody() MedicationDto dto) throws BusinessException, ParseException {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um Medicamento")
    public ResponseEntity<MedicationDto> findById(@PathVariable Integer id) throws BusinessException {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover um Medicamento")
    public void remove(@PathVariable() Integer id) throws BusinessException {
        service.remove(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um Medicamento")
    public  ResponseEntity<MedicationDto> update(@RequestBody() MedicationDto dto,
                                                       @PathVariable() Integer id) throws BusinessException, ParseException {
        return ResponseEntity.ok(service.update(dto,id));
    }

    @GetMapping
    @ApiOperation(value = "Busca todos os Medicamentos")
    public ResponseEntity<List<MedicationDto>> findAll() throws BusinessException {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Busca por nome ou número de registro anvisa")
    public ResponseEntity<List<MedicationDto>> findByAnvisaNumberOrName(@RequestParam() String filter) {
        return ResponseEntity.ok(service.findByAnvisaNumberOrName(filter));
    }

    @GetMapping("/check-anvisa-registration-number")
    @ApiOperation(value = "Verifica se número de registro da anvisa já existe")
    public ResponseEntity<Boolean> checkAnvisaRegistrationNumber(@RequestParam() String anvisaRegistrationNumber) throws ParseException {
        return ResponseEntity.ok(service.checkAnvisaRegistrationNumber(anvisaRegistrationNumber));
    }
}
