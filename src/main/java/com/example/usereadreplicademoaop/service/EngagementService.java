package com.example.usereadreplicademoaop.service;

import com.example.usereadreplicademoaop.annotation.ReadOnlyQuery;
import com.example.usereadreplicademoaop.dto.EngagementDTO;
import com.example.usereadreplicademoaop.mapper.EngagementMapper;
import com.example.usereadreplicademoaop.model.Engagement;
import com.example.usereadreplicademoaop.repositories.EngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pgayal
 * created on 03/03/2022
 */
@Component
public class EngagementService {

    final EngagementRepository engagementRepository;
    final EngagementMapper engagementMapper;

    @Autowired
    public EngagementService(EngagementRepository engagementRepository, EngagementMapper engagementMapper) {
        this.engagementRepository = engagementRepository;
        this.engagementMapper = engagementMapper;
    }

    public EngagementDTO saveEngagements(EngagementDTO engagementDTO) {
        Engagement saved = engagementRepository.save(engagementMapper.dtoToEntity(engagementDTO));
        return engagementMapper.entityToDTO(saved);
    }

    /**
     * This method has @ReadOnlyQuery annotation so ALL the queries done in this method will be executed on read-replica
     */
    @ReadOnlyQuery
    public List<EngagementDTO> getEngagements() {
        List<Engagement> engagements = engagementRepository.findAll();
        return engagementMapper.entityListToDTOList(engagements);
    }
}
