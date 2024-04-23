package cz.teddy.matcher.service.domain
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp

data class Transaction(
    var id : Int? = 0,
    var transactionId: String? = null,
    var date: Timestamp? = null,
    var constantSymbol: String? = null,
    var variableSymbol: String? = null,
    var accountNumberFull: String? = null,
    var amount: BigDecimal? = null,
    var comment: String? = null,
    var currency: String? = null,
    var createdAt: Timestamp? = null
)
