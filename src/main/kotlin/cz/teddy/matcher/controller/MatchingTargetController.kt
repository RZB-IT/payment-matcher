package cz.teddy.matcher.controller

import com.example.controllers.MatchingTargetController
import com.example.models.MatchingTarget
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller

@Controller
class MatchingTargetController : MatchingTargetController {
    override fun get(): HttpResponse<List<MatchingTarget>> {
        TODO("Not yet implemented")
    }

    override fun post(matchingTarget: MatchingTarget): HttpResponse<MatchingTarget> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): HttpResponse<MatchingTarget> {
        TODO("Not yet implemented")
    }

    override fun putById(matchingTarget: MatchingTarget, id: Int): HttpResponse<MatchingTarget> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): HttpResponse<Unit> {
        TODO("Not yet implemented")
    }
}