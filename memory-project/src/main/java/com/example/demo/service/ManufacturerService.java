package com.example.demo.service;

import com.example.demo.dto.ManufacturerDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    Logger logger = LoggerFactory.getLogger(ManufacturerService.class);

    @Autowired
    private ManufacturerRepository repository;

    public Manufacturer parseToEntity(ManufacturerDto dto){
        Manufacturer entity = new Manufacturer();

        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setName(dto.getName());

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

    public ManufacturerDto parseToDto(Manufacturer entity){
        ManufacturerDto dto = new ManufacturerDto();

        if(entity.getId() != null)
            dto.setId(entity.getId());
        dto.setName(entity.getName());

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

    public List<Manufacturer> parseListToEntity(List<ManufacturerDto> list){
        return list.stream().map(this::parseToEntity).collect(Collectors.toList());
    }

    public List<ManufacturerDto> parseListToDto(List<Manufacturer> list){
        return list.stream().map(this::parseToDto).collect(Collectors.toList());
    }

    public ManufacturerDto save(ManufacturerDto dto) throws BusinessException {

        Manufacturer manufacturer = new Manufacturer();

        if (dto.getName() == null){
            throw new BusinessException("Nome do fabricante não informado!");
        }

        logger.info("Salvando fabricante "+dto.getName()+" !");

        try{
            manufacturer = repository.save(parseToEntity(dto));
            return parseToDto(manufacturer);
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao registrar fabricante '"+dto.getName()+"'!");
        }
    }

    public ManufacturerDto update(ManufacturerDto dto, Integer id) throws BusinessException {

        Manufacturer manufacturer = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhum fabricante encontrada com o id: "+ id + "!"));

        manufacturer.setName(dto.getName());
        manufacturer.setLastUpdate(LocalDateTime.now());

        logger.info("Alterando fabricante "+manufacturer.getName()+"!");

        try {
            return parseToDto(repository.save(manufacturer));
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao atualizaar fabricante '"+manufacturer.getName()+"'!");
        }
    }

    public void remove(Integer id) throws BusinessException {

            Manufacturer manufacturer = repository.findByIdAndDeletedFalse(id)
                    .orElseThrow(() -> new BusinessException("Nenhum fabricante encontrada com o id: " + id + "!"));

            manufacturer.setDeleted(true);
            manufacturer.setLastUpdate(LocalDateTime.now());

            logger.info("Removendo fabricante "+manufacturer.getName()+"!");

            try{
                repository.save(manufacturer);
            }catch(Exception e){
                e.printStackTrace();
                throw new BusinessException("Erro ao remover fabricante '"+manufacturer.getName()+"'!");
            }
    }

    public ManufacturerDto findById(Integer id) throws BusinessException {
        return parseToDto(repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhum fabricante encontrada com o id: "+ id + "!")));
    }

    public List<ManufacturerDto> findAll(){
        return parseListToDto(repository.findAllWhereDeletedIsFalse());
    }

    public List<ManufacturerDto> findByName(String filter){
        return repository.findByName(filter)
                .stream().map(this::parseToDto).collect(Collectors.toList());
    }
}
