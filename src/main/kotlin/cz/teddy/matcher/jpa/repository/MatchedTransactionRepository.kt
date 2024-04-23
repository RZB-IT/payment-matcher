package cz.teddy.matcher.jpa.repository

import cz.teddy.matcher.jpa.entity.MatchedTransactionEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface MatchedTransactionRepository: CrudRepository<MatchedTransactionEntity, Int> {
    fun findByTargetIdIsNull() : List<MatchedTransactionEntity>

    fun findByTargetIdIsNotNull() : List<MatchedTransactionEntity>
}