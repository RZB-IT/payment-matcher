package cz.teddy.matcher.dto.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.Int
import kotlin.String

data class MatchingTargetDTO(
  @param:JsonProperty("id")
  @get:JsonProperty("id")
  val id: Int? = null,
  @param:JsonProperty("firstName")
  @get:JsonProperty("firstName")
  val firstName: String? = null,
  @param:JsonProperty("lastName")
  @get:JsonProperty("lastName")
  val lastName: String? = null,
  @param:JsonProperty("address")
  @get:JsonProperty("address")
  val address: String? = null,
  @param:JsonProperty("iban")
  @get:JsonProperty("iban")
  val iban: String? = null,
  @param:JsonProperty("variableSymbol")
  @get:JsonProperty("variableSymbol")
  val variableSymbol: String? = null,
  @param:JsonProperty("specificSymbol")
  @get:JsonProperty("specificSymbol")
  val specificSymbol: String? = null,
  @param:JsonProperty("constantSymbol")
  @get:JsonProperty("constantSymbol")
  val constantSymbol: String? = null,
  @param:JsonProperty("reference")
  @get:JsonProperty("reference")
  val reference: String? = null,
  @param:JsonProperty("identifier")
  @get:JsonProperty("identifier")
  val identifier: String? = null
)
