package cz.teddy.matcher.jpa.entity

import jakarta.persistence.Basic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.engine.internal.Cascade
import java.sql.Timestamp
import java.util.Objects

@Entity
@Table(name = "matched_transaction", schema = "public")
data class MatchedTransactionEntity (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @OneToOne(targetEntity = TransactionEntity::class, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "transaction_id")
    var transaction: TransactionEntity,

    @OneToOne(targetEntity = MatchingTargetEntity::class, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "target_id")
    var target: MatchingTargetEntity? = null,

    @Basic
    @Column(name = "created")
    var created: Timestamp? = null,
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchedTransactionEntity

        if (id != other.id) return false
        if (transaction != other.transaction) return false
        if (target != other.target) return false
        if (created != other.created) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (transaction.hashCode())
        result = 31 * result + (target?.hashCode() ?: 0)
        result = 31 * result + (created?.hashCode() ?: 0)
        return result
    }
}
