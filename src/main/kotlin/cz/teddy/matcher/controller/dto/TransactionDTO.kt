package cz.teddy.matcher.client

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
data class TransactionDTO(
    @JsonProperty("transaction_id")
    val transactionId: String,
    val date: String,
    @JsonProperty("constant_symbol")
    val constantSymbol: String?,
    @JsonProperty("variable_symbol")
    val variableSymbol: String?,
    @JsonProperty("account_number_full")
    val accountNumberFull: String?,
    val amount: String,
    val currency: String,
    val comment: String?,
    @JsonProperty("created_at")
    val createdAt: String
)
