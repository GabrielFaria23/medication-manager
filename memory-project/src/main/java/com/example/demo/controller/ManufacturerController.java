package com.example.demo.controller;

import com.example.demo.dto.ManufacturerDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(value = "Endpoint de Fabricantes", description = "Endpoint utilizado para fazer operações na entidade de Fabricantes", tags = "Endpoint de Fabricantes")
@RestController
@RequestMapping("/v1/manufacturer")
public class ManufacturerController {

    @Autowired
    private ManufacturerService service;

    @PostMapping
    @ApiOperation(value = "Registra um Fabricante")
    public ResponseEntity<ManufacturerDto> save(@RequestBody() ManufacturerDto dto) throws BusinessException {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um Fabricante")
    public ResponseEntity<ManufacturerDto> findById(@PathVariable Integer id) throws BusinessException {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover um Fabricante")
    public void remove(@PathVariable() Integer id) throws BusinessException {
        service.remove(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um Fabricante")
    public  ResponseEntity<ManufacturerDto> update(@RequestBody() ManufacturerDto dto,
                                                       @PathVariable() Integer id) throws BusinessException {
        return ResponseEntity.ok(service.update(dto,id));
    }

    @GetMapping
    @ApiOperation(value = "Busca todos os Fabricantes")
    public ResponseEntity<List<ManufacturerDto>> findAll() throws BusinessException {
        return ResponseEntity.ok(service.findAll());
    }
}
