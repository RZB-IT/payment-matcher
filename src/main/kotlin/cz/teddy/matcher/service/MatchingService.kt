package cz.teddy.matcher.service

import cz.teddy.matcher.jpa.entity.MatchedTransactionEntity
import cz.teddy.matcher.jpa.entity.MatchingTargetEntity
import cz.teddy.matcher.jpa.entity.TransactionEntity
import cz.teddy.matcher.jpa.repository.MatchedTransactionRepository
import cz.teddy.matcher.jpa.repository.MatchingTargetRepository
import cz.teddy.matcher.jpa.repository.TransactionRepository
import cz.teddy.matcher.mapper.MatchedTransactionMapper
import cz.teddy.matcher.mapper.MatchingTargetMapper
import cz.teddy.matcher.mapper.TransactionMapper
import cz.teddy.matcher.service.domain.MatchedTransaction
import cz.teddy.matcher.service.domain.MatchingTarget
import io.micronaut.cache.annotation.Cacheable
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

@Singleton
class MatchingService(
    val matchingTargetRepository: MatchingTargetRepository,
    val transactionRepository: TransactionRepository,
    val matchedTransactionRepository: MatchedTransactionRepository,
    val matchedTransactionMapper: MatchedTransactionMapper,
    val transactionService: TransactionService,
    val matchingTargetService: MatchingTargetService,
) {

    init {
        transactionService.load()
    }

    private val LOGGER: Logger = LoggerFactory.getLogger(MatchingService::class.java)
    fun match(): List<MatchedTransaction> {
        val transactions = transactionRepository.findUnmatched()
        val successfulMatched = ArrayList<MatchedTransaction>()
        for (transaction in transactions) {
            val matchedTransaction = matchTransaction(transaction)
            if (matchedTransaction.target != null) {
                successfulMatched.add(matchedTransaction)
            }
        }
        return successfulMatched
    }

    private fun getMatchedTransaction(transactionEntity: TransactionEntity): MatchedTransactionEntity {
        val matchedTransactionEntity = matchedTransactionRepository.findByTransaction(transactionEntity)
        return if (matchedTransactionEntity.isPresent) {
            matchedTransactionEntity.get()
        } else {
            MatchedTransactionEntity(
                transaction = transactionEntity,
                target = null,
                created = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            )
        }
    }

    private fun matchTransaction(transactionEntity: TransactionEntity): MatchedTransaction {
        val matchedTransaction: MatchedTransactionEntity = getMatchedTransaction(transactionEntity)
        val targets = getAllTargets()
        val variableSymbols = targets.filter { it.variableSymbol == transactionEntity.variableSymbol }
        val references = targets.filter { transactionEntity.comment?.contains(it.reference.toString()) ?: false }
        val identifiers =
            targets.filter { transactionEntity.variableSymbol == it.identifier || transactionEntity.comment?.contains(it.identifier.toString()) ?: false }

        val matchedTargets = HashSet<MatchingTargetEntity>().apply {
            addAll(references)
            addAll(identifiers)
            addAll(variableSymbols)
        }

        if (matchedTargets.size == 1) {
            matchedTransaction.target = matchedTargets.single()

        } else {
            if (matchedTargets.size > 1){
                //TODO create probable list
            }
            if (matchedTargets.size == 0 && transactionEntity.variableSymbol!= null && transactionEntity.variableSymbol?.length==5 ){
                var variableSymbol = transactionEntity.variableSymbol?.drop(2) ?: ""
                variableSymbol.dropWhile { it == '0' }
                val extraTargets = targets.filter { it.variableSymbol == variableSymbol }
                if(extraTargets.size == 1){
                    matchedTransaction.target = extraTargets.single()
                }
            }
            LOGGER.debug("Found {} records for transaction {}", matchedTargets.size, transactionEntity.transactionId)

        }
        matchedTransactionRepository.update(matchedTransaction)

        return matchedTransactionMapper.mapEntityToService(matchedTransaction)
    }

    fun getAllTargets(): List<MatchingTargetEntity> {
        return matchingTargetRepository.findAll()
    }
}