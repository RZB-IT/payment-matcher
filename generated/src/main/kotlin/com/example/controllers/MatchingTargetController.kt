package com.example.controllers

import com.example.models.MatchingTarget
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
   * Get all MatchingTarget
   */
  @Get(uri = "/matching-target")
  @Produces(value = ["application/json"])
  fun get(): HttpResponse<List<MatchingTarget>>

  /**
   * Create a MatchingTarget
   *
   * @param matchingTarget 
   */
  @Post(uri = "/matching-target")
  @Consumes(value = ["application/json"])
  @Produces(value = ["application/json"])
  fun post(@Body @Valid matchingTarget: MatchingTarget): HttpResponse<MatchingTarget>

  /**
   * Get MatchingTarget by ID
   *
   * @param id 
   */
  @Get(uri = "/matching-target/{id}")
  @Produces(value = ["application/json"])
  fun getById(@PathVariable(value = "id") id: Int): HttpResponse<MatchingTarget>

  /**
   * Update MatchingTarget by ID
   *
   * @param matchingTarget 
   * @param id 
   */
  @Put(uri = "/matching-target/{id}")
  @Consumes(value = ["application/json"])
  @Produces(value = ["application/json"])
  fun putById(@Body @Valid matchingTarget: MatchingTarget, @PathVariable(value = "id") id: Int):
      HttpResponse<MatchingTarget>

  /**
   * Delete MatchingTarget by ID
   *
   * @param id 
   */
  @Delete(uri = "/matching-target/{id}")
  fun deleteById(@PathVariable(value = "id") id: Int): HttpResponse<Unit>
}
