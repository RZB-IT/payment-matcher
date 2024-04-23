package cz.teddy.matcher.jpa.repository

import cz.teddy.matcher.jpa.entity.MatchingTargetEntity
import cz.teddy.matcher.jpa.entity.TransactionEntity
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@Repository
interface TransactionRepository : CrudRepository<TransactionEntity,Int> {

    fun findByTransactionId(transactionId : String) : Optional<TransactionEntity>

    @Query("FROM TransactionEntity t left join MatchedTransactionEntity m on t.id = m.transaction.id where m.target is null")
    fun findUnmatched() : List<TransactionEntity>
}