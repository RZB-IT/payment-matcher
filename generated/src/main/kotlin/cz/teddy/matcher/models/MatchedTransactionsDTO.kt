package cz.teddy.matcher.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import kotlin.Long
import kotlin.collections.List

data class MatchedTransactionsDTO(
  @param:JsonProperty("targetId")
  @get:JsonProperty("targetId")
  @get:NotNull
  val targetId: Long,
  @param:JsonProperty("transactions")
  @get:JsonProperty("transactions")
  @get:NotNull
  @get:Valid
  val transactions: List<TransactionDTO>
)
