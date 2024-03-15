package cz.teddy.matcher.domain
import java.math.BigInteger
import java.sql.Timestamp

data class Transaction(
    var transactionId: String? = null,
    var date: Timestamp? = null,
    var constantSymbol: String? = null,
    var variableSymbol: String? = null,
    var accountNumberFull: String? = null,
    var amount: BigInteger? = null,
    var comment: String? = null,
    var currency: String? = null,
    var createdAt: Timestamp? = null
)
