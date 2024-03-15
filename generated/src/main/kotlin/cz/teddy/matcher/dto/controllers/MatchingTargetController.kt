package cz.teddy.matcher.dto.controllers

import cz.teddy.matcher.dto.models.MatchingTargetDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Put
import jakarta.validation.Valid
import kotlin.Int
import kotlin.Unit
import kotlin.collections.List

@Controller
interface MatchingTargetController {
  /**
   * Get all MatchingTargetDTO
   */
  @Get(uri = "/matching-target")
  @Produces(value = ["application/json"])
  fun get(): HttpResponse<List<MatchingTargetDTO>>

  /**
   * Create a MatchingTargetDTO
   *
   * @param matchingTargetDTO 
   */
  @Post(uri = "/matching-target")
  @Consumes(value = ["application/json"])
  @Produces(value = ["application/json"])
  fun post(@Body @Valid matchingTargetDTO: MatchingTargetDTO): HttpResponse<MatchingTargetDTO>

  /**
   * Get MatchingTargetDTO by ID
   *
   * @param id 
   */
  @Get(uri = "/matching-target/{id}")
  @Produces(value = ["application/json"])
  fun getById(@PathVariable(value = "id") id: Int): HttpResponse<MatchingTargetDTO>

  /**
   * Update MatchingTargetDTO by ID
   *
   * @param matchingTargetDTO 
   * @param id 
   */
  @Put(uri = "/matching-target/{id}")
  @Consumes(value = ["application/json"])
  @Produces(value = ["application/json"])
  fun putById(@Body @Valid matchingTargetDTO: MatchingTargetDTO, @PathVariable(value = "id")
      id: Int): HttpResponse<MatchingTargetDTO>

  /**
   * Delete MatchingTargetDTO by ID
   *
   * @param id 
   */
  @Delete(uri = "/matching-target/{id}")
  fun deleteById(@PathVariable(value = "id") id: Int): HttpResponse<Unit>
}
