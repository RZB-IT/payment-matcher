package cz.teddy.matcher.controller

import cz.teddy.matcher.controller.dto.MatchedTransactionsDTO
import cz.teddy.matcher.mapper.MatchedTransactionMapper
import cz.teddy.matcher.service.MatchedTransactionService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/transactions")
class MatchedTransactionController(
    val matchedTransactionService: MatchedTransactionService,
    val matchedTransactionMapper: MatchedTransactionMapper
) {

    @Get
    fun get() : List<MatchedTransactionsDTO> {
       return matchedTransactionService.getMatchedTransactions().map { matchedTransactionMapper.mapServiceToDto(it) }
    }

    @Get("/unmatched")
    fun getUnmatched() : List<MatchedTransactionsDTO> {
        return matchedTransactionService.getUnmatchedTransactions().map { matchedTransactionMapper.mapServiceToDto(it) }
    }
}