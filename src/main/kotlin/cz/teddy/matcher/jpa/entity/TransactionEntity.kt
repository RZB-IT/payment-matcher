package cz.teddy.matcher.jpa.entity

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import java.util.Objects

@Entity
@Table(name = "transaction", schema = "public")
class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="id")
    var id: Int = 0

    @Column(name = "transaction_id", unique = true)
    var transactionId: String? = null

    @Basic
    @Column(name = "date")
    var date: Timestamp? = null

    @Basic
    @Column(name = "constant_symbol")
    var constantSymbol: String? = null

    @Basic
    @Column(name = "variable_symbol")
    var variableSymbol: String? = null

    @Basic
    @Column(name = "account_number_full")
    var accountNumberFull: String? = null

    @Basic
    @Column(name = "amount")
    var amount: BigDecimal? = null

    @Basic
    @Column(name = "comment")
    var comment: String? = null

    @Basic
    @Column(name = "currency")
    var currency: String? = null

    @Basic
    @Column(name = "created_at")
    var createdAt: Timestamp? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as TransactionEntity
        return transactionId == that.transactionId && date == that.date && constantSymbol == that.constantSymbol && variableSymbol == that.variableSymbol && accountNumberFull == that.accountNumberFull && amount == that.amount && comment == that.comment && currency == that.currency && createdAt == that.createdAt
    }

    override fun hashCode(): Int {
        return Objects.hash(transactionId, date, constantSymbol, variableSymbol, accountNumberFull, amount, comment, currency, createdAt)
    }
}
