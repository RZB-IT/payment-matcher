package cz.teddy.matcher.service

import cz.teddy.matcher.jpa.repository.MatchedTransactionRepository
import cz.teddy.matcher.mapper.MatchedTransactionMapper
import cz.teddy.matcher.service.domain.MatchedTransaction
import jakarta.inject.Singleton

@Singleton
class MatchedTransactionService(
    val matchedTransactionRepository: MatchedTransactionRepository,
    val matchedTransactionMapper: MatchedTransactionMapper,
    val matchingService: MatchingService
) {
    fun getMatchedTransactions() : List<MatchedTransaction>{
        matchingService.match()
       return matchedTransactionRepository.findByTargetIdIsNotNull().map { matchedTransactionMapper.mapEntityToService(it) }
    }
    fun getUnmatchedTransactions() : List<MatchedTransaction>{
        matchingService.match()
       return matchedTransactionRepository.findByTargetIdIsNull().map { matchedTransactionMapper.mapEntityToService(it) }
    }
}