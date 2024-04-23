package cz.teddy.matcher.controller

import cz.teddy.matcher.controller.dto.MatchedTransactionsDTO
import cz.teddy.matcher.mapper.MatchedTransactionMapper
import cz.teddy.matcher.mapper.TransactionMapper
import cz.teddy.matcher.service.MatchingService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.time.LocalDate
import java.time.LocalDateTime

@Controller("/matcher")
class MatcherController(
    val matchingService: MatchingService,
    val transactionMapper: TransactionMapper,
    val matchedTransactionMapper: MatchedTransactionMapper,
)  {
    @Get
    fun get(
//        from: LocalDate, to: LocalDate
    ): HttpResponse<List<MatchedTransactionsDTO>> {
        val matchedTransactions = matchingService.match(LocalDateTime.now())
        return HttpResponse.ok(matchingService.match(LocalDateTime.now()).map {
            matchedTransactionMapper.mapServiceToDto(it)
        })
    }

    fun getById(
        from: LocalDate,
        to: LocalDate,
        matchingTargetDTOId: Int
    ): HttpResponse<List<MatchedTransactionsDTO>> {
        return HttpResponse.ok(emptyList())
        TODO("Not yet implemented")

    }


}
