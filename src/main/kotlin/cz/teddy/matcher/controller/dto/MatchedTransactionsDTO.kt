package cz.teddy.matcher.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal

@Serdeable
class MatchedTransactionsDTO(
    var identifier: String?,
    @JsonProperty("transaction_id")
    var transactionId: String,
    @JsonProperty("transaction_amount")
    var transactionAmount: BigDecimal,
) {

}