package cz.teddy.matcher.mapper

import cz.teddy.matcher.domain.MatchingTarget
import cz.teddy.matcher.dto.models.MatchingTargetDTO
import org.mapstruct.Mapper

@Mapper
interface MatchingTargetMapper {
    fun mapToService(matchingTargetDTO: MatchingTargetDTO) : MatchingTarget
}