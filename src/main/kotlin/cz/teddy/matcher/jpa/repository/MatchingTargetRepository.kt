package cz.teddy.matcher.jpa.repository

import cz.teddy.matcher.jpa.entity.MatchingTargetEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import org.intellij.lang.annotations.Identifier
import java.util.Optional

@Repository
interface MatchingTargetRepository : CrudRepository<MatchingTargetEntity,Int> {
    fun findByIdentifier(identifier: String): Optional<MatchingTargetEntity>
}