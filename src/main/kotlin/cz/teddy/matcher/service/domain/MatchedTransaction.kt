package cz.teddy.matcher.service.domain

import java.time.LocalDateTime
import java.time.OffsetDateTime

data class MatchedTransaction (
    val id: Int? = null,
    val transaction: Transaction,
    var target: MatchingTarget?,
    val created: LocalDateTime
)