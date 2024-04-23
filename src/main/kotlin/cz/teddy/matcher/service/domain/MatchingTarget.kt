package cz.teddy.matcher.service.domain

data class MatchingTarget(
    var id: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var address: String? = null,
    var iban: String? = null,
    var variableSymbol: String? = null,
    var specificSymbol: String? = null,
    var constantSymbol: String? = null,
    var reference: String? = null,
    var identifier: String? = null
)