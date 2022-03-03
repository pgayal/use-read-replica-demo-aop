package com.example.usereadreplicademoaop.mapper;

import com.example.usereadreplicademoaop.dto.EngagementDTO;
import com.example.usereadreplicademoaop.model.Engagement;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author pgayal
 * created on 03/03/2022
 */
@Mapper(componentModel = "spring")
public interface EngagementMapper {

    EngagementDTO entityToDTO(Engagement engagement);

    Engagement dtoToEntity(EngagementDTO engagementDTO);

    List<EngagementDTO> entityListToDTOList(List<Engagement> engagements);

    List<Engagement> dtoListToEntityList(List<EngagementDTO> dtos);
}
