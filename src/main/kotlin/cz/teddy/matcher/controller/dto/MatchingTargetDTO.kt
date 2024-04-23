package cz.teddy.matcher.controller.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class MatchingTargetDTO (
    var id: Int? = 0,
    @JsonProperty("first_name")
    var firstName: String? = null,
    @JsonProperty("last_name")
    var lastName: String? = null,
    var address: String? = null,
    var iban: String? = null,
    @JsonProperty("variable_symbol")
    var variableSymbol: String? = null,
    @JsonProperty("specific_symbol")
    var specificSymbol: String? = null,
    @JsonProperty("constant_symbol")
    var constantSymbol: String? = null,
    var reference: String? = null,
    var identifier: String? = null
)