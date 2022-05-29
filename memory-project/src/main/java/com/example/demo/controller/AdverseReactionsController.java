package com.example.demo.controller;

import com.example.demo.dto.AdverseReactionsDto;
import com.example.demo.dto.MedicationDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.AdverseReactionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(value = "Endpoint de Reações Adversas", description = "Endpoint utilizado para fazer operações na entidade de Reações Adversas", tags = "Endpoint de Reações Adversas")
@RestController
@RequestMapping("/v1/adverse-reactions")
public class AdverseReactionsController {

    @Autowired
    private AdverseReactionsService service;

    @PostMapping
    @ApiOperation(value = "Registra uma Reação Adversa")
    public ResponseEntity<AdverseReactionsDto> save(@RequestBody() AdverseReactionsDto dto) throws BusinessException {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar uma Reação Adversa")
    public ResponseEntity<AdverseReactionsDto> findById(@PathVariable Integer id) throws BusinessException {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover uma Reação Adversa")
    public void remove(@PathVariable() Integer id) throws BusinessException {
        service.remove(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar uma Reação Adversa")
    public  ResponseEntity<AdverseReactionsDto> update(@RequestBody() AdverseReactionsDto dto,
                                                       @PathVariable() Integer id) throws BusinessException {
        return ResponseEntity.ok(service.update(dto,id));
    }

    @GetMapping
    @ApiOperation(value = "Busca todas as Reação Adversa")
    public ResponseEntity<List<AdverseReactionsDto>> findAll() throws BusinessException {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Busca por descrição")
    public ResponseEntity<List<AdverseReactionsDto>> findByDescription(@RequestParam() String filter) {
        return ResponseEntity.ok(service.findByDescription(filter));
    }
}
