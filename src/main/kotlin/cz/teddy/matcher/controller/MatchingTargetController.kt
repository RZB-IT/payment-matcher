package cz.teddy.matcher.controller

import cz.teddy.matcher.controller.dto.MatchingTargetDTO
import cz.teddy.matcher.service.domain.exception.NotFoundException
import cz.teddy.matcher.mapper.MatchingTargetMapper
import cz.teddy.matcher.service.MatchingTargetService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/targets")
class MatchingTargetController(
    val matchingTargetService: MatchingTargetService,
    val mapper: MatchingTargetMapper
)  {
    @Get(produces = [MediaType.APPLICATION_JSON])
     fun get(): HttpResponse<List<MatchingTargetDTO>> {
        return HttpResponse.ok(matchingTargetService.getAll().map { mapper.mapServiceToDto(it) })
    }

    @Post(produces = [MediaType.APPLICATION_JSON],
        consumes = [MediaType.APPLICATION_JSON] )
     fun post(@Body matchingTargetDTO: MatchingTargetDTO): HttpResponse<MatchingTargetDTO> {
        val matchingTarget = matchingTargetService.create(mapper.mapDtoToService(matchingTargetDTO))
        return HttpResponse.created(mapper.mapServiceToDto(matchingTarget))
    }

     fun getById(id: Int): HttpResponse<MatchingTargetDTO> {
        return try {
            HttpResponse.ok(mapper.mapServiceToDto(matchingTargetService.getById(id)))
        } catch (e: NotFoundException) {
            HttpResponse.notFound()
        }
    }

     fun putById(matchingTargetDTO: MatchingTargetDTO, id: Int): HttpResponse<MatchingTargetDTO> {
        matchingTargetService.update(mapper.mapDtoToService(matchingTargetDTO), id)
        return HttpResponse.ok()
    }

     fun deleteById(id: Int): HttpResponse<Unit> {
        matchingTargetService.delete(id)
        return HttpResponse.ok()
    }


}