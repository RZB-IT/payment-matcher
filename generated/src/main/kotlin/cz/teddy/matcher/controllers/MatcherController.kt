package cz.teddy.matcher.controllers

import cz.teddy.matcher.models.MatchedTransactionsDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import java.time.LocalDate
import kotlin.Int
import kotlin.collections.List

@Controller
interface MatcherController {
  /**
   * Get all matched transactions
   *
   * @param from Start date (YYYY-MM-DD)
   * @param to End date (YYYY-MM-DD)
   */
  @Get(uri = "/matcher")
  @Produces(value = ["application/json"])
  fun get(@QueryValue(value = "from") from: LocalDate, @QueryValue(value = "to") to: LocalDate):
      HttpResponse<List<MatchedTransactionsDTO>>

  /**
   * Get all matched transactions
   *
   * @param from Start date (YYYY-MM-DD)
   * @param to End date (YYYY-MM-DD)
   * @param matchingTargetDTOId 
   */
  @Get(uri = "/matcher/{MatchingTargetDTOId}")
  @Produces(value = ["application/json"])
  fun getById(
    @QueryValue(value = "from") from: LocalDate,
    @QueryValue(value = "to") to: LocalDate,
    @PathVariable(value = "MatchingTargetDTOId") matchingTargetDTOId: Int
  ): HttpResponse<MatchedTransactionsDTO>
}
