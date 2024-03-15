package com.example.controllers

import com.example.models.MatchedTransactions
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
      HttpResponse<List<MatchedTransactions>>

  /**
   * Get all matched transactions
   *
   * @param from Start date (YYYY-MM-DD)
   * @param to End date (YYYY-MM-DD)
   * @param matchingTargetId 
   */
  @Get(uri = "/matcher/{matchingTargetId}")
  @Produces(value = ["application/json"])
  fun getById(
    @QueryValue(value = "from") from: LocalDate,
    @QueryValue(value = "to") to: LocalDate,
    @PathVariable(value = "MatchingTargetId") matchingTargetId: Int
  ): HttpResponse<MatchedTransactions>
}
