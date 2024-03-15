package com.example.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime
import kotlin.Long
import kotlin.String

data class Transaction(
  @param:JsonProperty("transactionId")
  @get:JsonProperty("transactionId")
  @get:NotNull
  val transactionId: String,
  @param:JsonProperty("date")
  @get:JsonProperty("date")
  @get:NotNull
  val date: OffsetDateTime,
  @param:JsonProperty("constantSymbol")
  @get:JsonProperty("constantSymbol")
  val constantSymbol: String? = null,
  @param:JsonProperty("variableSymbol")
  @get:JsonProperty("variableSymbol")
  val variableSymbol: String? = null,
  @param:JsonProperty("accountNumberFull")
  @get:JsonProperty("accountNumberFull")
  val accountNumberFull: String? = null,
  @param:JsonProperty("amount")
  @get:JsonProperty("amount")
  @get:NotNull
  val amount: Long,
  @param:JsonProperty("comment")
  @get:JsonProperty("comment")
  val comment: String? = null,
  @param:JsonProperty("currency")
  @get:JsonProperty("currency")
  val currency: String? = null,
  @param:JsonProperty("createdAt")
  @get:JsonProperty("createdAt")
  val createdAt: OffsetDateTime? = null
)
