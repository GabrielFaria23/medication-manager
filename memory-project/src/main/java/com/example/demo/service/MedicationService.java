package com.example.demo.service;

import com.example.demo.dto.MedicationDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Medication;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.utils.FormatterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    Logger logger = LoggerFactory.getLogger(MedicationService.class);

    @Autowired
    private FormatterUtils utils;

    @Autowired
    private MedicationRepository repository;

    @Autowired
    private AdverseReactionsService adverseReactionsService;

    @Autowired
    private ManufacturerService manufacturerService;

    private Medication parseToEntity(MedicationDto dto){
        Medication entity = new Medication();

        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setAnvisaRegistrationNumber(dto.getAnvisaRegistrationNumber());
        entity.setName(dto.getName());
        entity.setTelephoneSac(dto.getTelephoneSac());
        entity.setAdverseReactions(adverseReactionsService.parseListToEntity(dto.getAdverseReactions()));
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setPrice(dto.getPrice());
        entity.setQuantityPills(dto.getQuantityPills());
        entity.setManufacturer(manufacturerService.parseToEntity(dto.getManufacturer()));

        if (dto.getActivated() != null)
            entity.setActivated(dto.getActivated());

        if (dto.getDeleted() != null)
            entity.setDeleted(dto.getDeleted());

        if (dto.getCreatedAt() != null)
            entity.setCreatedAt(dto.getCreatedAt());

        if (dto.getLastUpdate() != null)
            entity.setLastUpdate(dto.getLastUpdate());

        return entity;
    }

    private MedicationDto parseToDto(Medication entity){
        MedicationDto dto = new MedicationDto();

        if(entity.getId() != null)
            dto.setId(entity.getId());
        dto.setAnvisaRegistrationNumber(entity.getAnvisaRegistrationNumber());
        dto.setName(entity.getName());
        dto.setTelephoneSac(entity.getTelephoneSac());
        dto.setAdverseReactions(adverseReactionsService.parseListToDto(entity.getAdverseReactions()));
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setPrice(entity.getPrice());
        dto.setQuantityPills(entity.getQuantityPills());
        dto.setManufacturer(manufacturerService.parseToDto(entity.getManufacturer()));

        if (entity.getActivated() != null)
            dto.setActivated(entity.getActivated());

        if (entity.getDeleted() != null)
            dto.setDeleted(entity.getDeleted());

        if (entity.getCreatedAt() != null)
            dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getLastUpdate() != null)
            dto.setLastUpdate(entity.getLastUpdate());

        return dto;
    }

    private List<Medication> parseListToEntity(List<MedicationDto> list){
        return list.stream().map(this::parseToEntity).collect(Collectors.toList());
    }

    private List<MedicationDto> parseListToDto(List<Medication> list){
        return list.stream().map(this::parseToDto).collect(Collectors.toList());
    }

    public MedicationDto save(MedicationDto dto) throws BusinessException, ParseException {

        Medication medication = checkInfoMedication(dto);

        validateFields(medication);

        logger.info("Registrando medicamento "+medication.getName()+"!");

        try{
            return parseToDto(repository.save(medication));
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao registrar fabricante '"+medication.getName()+"'!");
        }
    }

    public MedicationDto update(MedicationDto dto, Integer id) throws BusinessException, ParseException {

        checkInfoMedication(dto);

        validateFields(parseToEntity(dto));

        Medication medication = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhum medicamento encontrado com o id: "+ id + "!"));

        BeanUtils.copyProperties(dto, medication, "id", "lastUpdate", "createdAt", "activated", "deleted", "adverseReactions", "manufacturer");
        medication.setAdverseReactions(adverseReactionsService.parseListToEntity(dto.getAdverseReactions()));
        medication.setManufacturer(manufacturerService.parseToEntity(dto.getManufacturer()));
        medication.setLastUpdate(LocalDateTime.now());

        logger.info("Alterando medicamento "+ medication.getName() +"!");

        try{
            return parseToDto(repository.save(medication));
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao atualizar fabricante '"+medication.getName()+"'!");
        }
    }

    public void remove(Integer id) throws BusinessException {

        Medication medication = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhum medicamento encontrado com o id: "+ id + "!"));

        medication.setDeleted(true);
        medication.setLastUpdate(LocalDateTime.now());

        logger.info("Removendo medicamento "+medication.getName()+"!");

        try{
            repository.save(medication);
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao remover fabricante '"+medication.getName()+"'!");
        }
    }

    public MedicationDto findById(Integer id) throws BusinessException {
        return parseToDto(repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhum medicamento encontrado com o id: "+ id + "!")));
    }

    public List<MedicationDto> findAll(){
        return parseListToDto(repository.findAllWhereDeletedIsFalse());
    }

    public Medication checkInfoMedication(MedicationDto dto) throws BusinessException {
        if (dto.getManufacturer() == null || dto.getExpirationDate() == null || dto.getAnvisaRegistrationNumber() == null
                || dto.getPrice() == null || dto.getName() == null || dto.getTelephoneSac() == null){
            throw new BusinessException("As informações do medicamento não foram informados corretamente!");
        }
        return parseToEntity(dto);
    }

    private Medication validateFields(Medication medication) throws BusinessException, ParseException {

        if (medication.getAnvisaRegistrationNumber().length() != 13){
            throw new BusinessException("Tamanho inválido para o número de registro da anvisa!");
        }
        medication.setAnvisaRegistrationNumber(utils.formatAnvisaNumber(medication.getAnvisaRegistrationNumber()));

        if (medication.getTelephoneSac().length() <= 10 && medication.getTelephoneSac().length() > 11){
            throw new BusinessException("Tamanho inválido para o número de registro da anvisa!");
        }
        medication.setTelephoneSac(utils.formatTelephoneSacNumber(medication.getTelephoneSac()));

        String validateAnvisaNumber = repository.findByAnvisaNumber(medication.getAnvisaRegistrationNumber());

        if (validateAnvisaNumber != null)
            throw new BusinessException("Número anvisa já cadastrado no sistema!");

        return medication;
    }

    public List<MedicationDto> findByAnvisaNumberOrName(String filter){
        return repository.findByAnvisaNumberOrName(filter)
                .stream().map(this::parseToDto).collect(Collectors.toList());
    }

    public boolean checkAnvisaRegistrationNumber(String anvisaRegistrationNumber) throws ParseException {
        return repository.findByAnvisaNumber(utils.formatAnvisaNumber(anvisaRegistrationNumber)) == null;
    }
}
