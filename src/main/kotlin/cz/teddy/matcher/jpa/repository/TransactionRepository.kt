package cz.teddy.matcher.jpa.repository

import cz.teddy.matcher.jpa.entity.MatchingTargetEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TransactionRepository : CrudRepository<MatchingTargetEntity,Int> {
}