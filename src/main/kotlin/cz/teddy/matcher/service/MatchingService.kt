package cz.teddy.matcher.service

import cz.teddy.matcher.jpa.entity.TransactionEntity
import cz.teddy.matcher.jpa.repository.MatchedTransactionRepository
import cz.teddy.matcher.jpa.repository.MatchingTargetRepository
import cz.teddy.matcher.jpa.repository.TransactionRepository
import cz.teddy.matcher.mapper.MatchedTransactionMapper
import cz.teddy.matcher.mapper.MatchingTargetMapper
import cz.teddy.matcher.mapper.TransactionMapper
import cz.teddy.matcher.service.domain.MatchedTransaction
import cz.teddy.matcher.service.domain.MatchingTarget
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@Singleton
class MatchingService(
    val matchingTargetRepository: MatchingTargetRepository,
    val transactionRepository: TransactionRepository,
    val matchingTargetMapper: MatchingTargetMapper,
    val transactionMapper: TransactionMapper,
    val matchedTransactionRepository: MatchedTransactionRepository,
    val matchedTransactionMapper: MatchedTransactionMapper,
    val transactionService: TransactionService,
) {

    private val LOGGER: Logger = LoggerFactory.getLogger(MatchingService::class.java)
     fun match(fromDateTime: LocalDateTime) : List<MatchedTransaction>  {
        val transactions = transactionRepository.findUnmatched()
        val successfulMatched = ArrayList<MatchedTransaction>()
        for (transaction in transactions){
           val matchedTransaction = matchTransaction(transaction)
            if (matchedTransaction.target !=null){
                successfulMatched.add(matchedTransaction)
            }
        }
        return successfulMatched
    }

    private fun matchTransaction(transactionEntity: TransactionEntity): MatchedTransaction {
        val matchedTransaction : MatchedTransaction
        val targets = getAllTargets()
        val variableSymbols = targets.filter { it.variableSymbol == transactionEntity.variableSymbol }
        val references = targets.filter { transactionEntity.comment?.contains(it.reference.toString()) ?: false }
        val identifiers =
            targets.filter { transactionEntity.variableSymbol == it.identifier || transactionEntity.comment?.contains(it.identifier.toString()) ?: false }

        val matchedTargets = HashSet<MatchingTarget>().apply {
            addAll(references)
            addAll(identifiers)
            addAll(variableSymbols)
        }

        if (matchedTargets.size == 1) {
            matchedTransaction = MatchedTransaction(
                transaction = transactionMapper.mapEntityToService(transactionEntity),
                target = matchedTargets.single(),
                created = LocalDateTime.now()
            )
        } else{
            LOGGER.warn("Found {} records for transaction {}",matchedTargets.size, transactionEntity.transactionId)
             matchedTransaction = MatchedTransaction(
                transaction = transactionMapper.mapEntityToService(transactionEntity),
                target = null,
                created = LocalDateTime.now()
            )
        }
        matchedTransactionRepository.save(matchedTransactionMapper.mapServiceToEntity(matchedTransaction))
        return matchedTransaction
    }

//    @Cacheable("targets")
    fun getAllTargets(): List<MatchingTarget> {
        return matchingTargetRepository.findAll().map { matchingTargetMapper.mapEntityToService(it) }
    }
}