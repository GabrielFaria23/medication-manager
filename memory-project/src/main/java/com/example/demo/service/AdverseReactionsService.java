package com.example.demo.service;

import com.example.demo.dto.AdverseReactionsDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.AdverseReactions;
import com.example.demo.repository.AdverseReactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdverseReactionsService {

    Logger logger = LoggerFactory.getLogger(AdverseReactionsService.class);

    @Autowired
    private AdverseReactionsRepository repository;

    private AdverseReactions parseToEntity(AdverseReactionsDto dto){
        AdverseReactions entity = new AdverseReactions();

        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());

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

    private AdverseReactionsDto parseToDto(AdverseReactions entity){
        AdverseReactionsDto dto = new AdverseReactionsDto();

        if(entity.getId() != null)
            dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());

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

    public List<AdverseReactions> parseListToEntity(List<AdverseReactionsDto> list){
        return list.stream().map(this::parseToEntity).collect(Collectors.toList());
    }

    public List<AdverseReactionsDto> parseListToDto(List<AdverseReactions> list){
        return list.stream().map(this::parseToDto).collect(Collectors.toList());
    }

    public AdverseReactionsDto save(AdverseReactionsDto dto) throws BusinessException {

        AdverseReactions adverseReactions = new AdverseReactions();

        if (dto.getDescription() == null){
            throw new BusinessException("Nenhuma descrição informada para a reação adversa!");
        }

        logger.info("Salvando reação adversa "+dto.getDescription()+" !");

        try{
            adverseReactions = repository.save(parseToEntity(dto));
            return parseToDto(adverseReactions);
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao registrar reação adversa '"+dto.getDescription()+"'!");
        }
    }

    public AdverseReactionsDto update(AdverseReactionsDto dto, Integer id) throws BusinessException {

        AdverseReactions adverseReaction = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhuma reação adversa encontrada com o id: "+ id + "!"));

        adverseReaction.setDescription(dto.getDescription());
        adverseReaction.setLastUpdate(LocalDateTime.now());

        logger.info("Alterando reação adversa '"+dto.getDescription()+"'!");

        try{
            return parseToDto(repository.save(adverseReaction));
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao alterar reação adversa '"+adverseReaction.getDescription()+"'!");
        }
    }

    public void remove(Integer id) throws BusinessException {

        if(repository.checkIfAdverseReactionIsNotViculedToAnyMedication(id) == 0) {

            AdverseReactions adverseReaction = repository.findByIdAndDeletedFalse(id)
                    .orElseThrow(() -> new BusinessException("Nenhuma reação adversa encontrada com o id: " + id + "!"));

            adverseReaction.setDeleted(true);
            adverseReaction.setLastUpdate(LocalDateTime.now());

            logger.info("Removendo reação adversa "+adverseReaction.getDescription()+"!");

            try {
                repository.save(adverseReaction);
            }catch(Exception e){
                e.printStackTrace();
                throw new BusinessException("Erro ao remover reação adversa '"+adverseReaction.getDescription()+"'!");
            }
        }else {
            throw new BusinessException("A Reação adversa com id: " + id + " não pode ser removida pois ainda esta vinculada a algum(s) remedio(s)");
        }
    }

    public AdverseReactionsDto findById(Integer id) throws BusinessException {
        return parseToDto(repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Nenhuma reação adversa encontrada com o id: "+ id + "!")));
    }

    public List<AdverseReactionsDto> findAll(){
        return parseListToDto(repository.findAllWhereDeletedIsFalse());
    }
}
