package cz.teddy.matcher.jpa.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.util.Objects

@Entity
@Table(name = "matched_transaction", schema = "public", catalog = "bankservice")
data class MatchedTransactionEntity (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Basic
    @Column(name = "transcation_id")
    var transcationId: String? = null,

    @Basic
    @Column(name = "entity_id")
    var entityId: Int? = null,

    @Basic
    @Column(name = "created")
    var created: Timestamp? = null,
){
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as MatchedTransactionEntity
        return id == that.id && transcationId == that.transcationId && entityId == that.entityId && created == that.created
    }

    override fun hashCode(): Int {
        return Objects.hash(id, transcationId, entityId, created)
    }
}
