package cz.teddy.matcher.jpa.repository

import cz.teddy.matcher.jpa.entity.MatchedTransactionEntity
import cz.teddy.matcher.jpa.entity.TransactionEntity
import cz.teddy.matcher.service.domain.Transaction
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@Repository
interface MatchedTransactionRepository: CrudRepository<MatchedTransactionEntity, Int> {
    fun findByTargetIdIsNull() : List<MatchedTransactionEntity>

    fun findByTargetIdIsNotNull() : List<MatchedTransactionEntity>

    fun findByTransaction(transaction: TransactionEntity) : Optional<MatchedTransactionEntity>
}