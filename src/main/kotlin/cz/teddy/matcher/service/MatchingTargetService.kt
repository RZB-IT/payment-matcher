package cz.teddy.matcher.service

import cz.teddy.matcher.service.domain.MatchingTarget
import cz.teddy.matcher.service.domain.exception.NotFoundException
import cz.teddy.matcher.jpa.repository.MatchingTargetRepository
import cz.teddy.matcher.mapper.MatchingTargetMapper
import jakarta.inject.Singleton

@Singleton
class MatchingTargetService(
    val matchingTargetRepository: MatchingTargetRepository,
    val matchingTargetMapper: MatchingTargetMapper
) {
    fun getAll(): List<MatchingTarget> {
        return matchingTargetRepository.findAll().map { matchingTargetMapper.mapEntityToService(it) }
    }

    fun getById(id: Int): MatchingTarget {
        val matchingTarget = matchingTargetRepository.findById(id)
        if (matchingTarget.isPresent) {
            return matchingTargetMapper.mapEntityToService(matchingTarget.get())
        } else {
            throw NotFoundException()
        }
    }

//    @CacheInvalidate("targets")
    fun create(matchingTarget: MatchingTarget): MatchingTarget {
        val entity = matchingTargetRepository.save(matchingTargetMapper.mapServiceToEntity(matchingTarget))
        return matchingTargetMapper.mapEntityToService(entity)
    }

//    @CacheInvalidate("targets")
    fun update(matchingTarget: MatchingTarget, id: Int) {
        val oldMatchingTarget = matchingTargetRepository.findById(id)
        if (oldMatchingTarget.isPresent) {
            matchingTargetRepository.update(matchingTargetMapper.mapServiceToEntity(matchingTarget))
        } else {
            throw NotFoundException()
        }
    }

    fun delete(id: Int) {
        matchingTargetRepository.deleteById(id)
    }
}