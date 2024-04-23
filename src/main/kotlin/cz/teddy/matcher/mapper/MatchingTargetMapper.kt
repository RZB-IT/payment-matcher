package cz.teddy.matcher.mapper

import cz.teddy.matcher.controller.dto.MatchingTargetDTO
import cz.teddy.matcher.service.domain.MatchingTarget
import cz.teddy.matcher.jpa.entity.MatchingTargetEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330",
    uses = [MapperUtils::class])
interface MatchingTargetMapper {
    fun mapDtoToService(matchingTargetDTO: MatchingTargetDTO): MatchingTarget

    fun mapEntityToService(matchingTargetEntity: MatchingTargetEntity): MatchingTarget

    fun mapServiceToEntity(matchingTarget: MatchingTarget): MatchingTargetEntity

    fun mapServiceToDto(matchingTarget: MatchingTarget): MatchingTargetDTO
}