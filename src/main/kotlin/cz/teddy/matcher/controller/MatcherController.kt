package cz.teddy.matcher.controller

import com.example.controllers.MatcherController
import com.example.controllers.MatchingTargetController
import com.example.models.MatchedTransactions
import com.example.models.MatchingTarget
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.time.LocalDate

@Controller
class MatcherController : MatcherController  {
    override fun get(from: LocalDate, to: LocalDate): HttpResponse<List<MatchedTransactions>> {
        TODO("Not yet implemented")
    }

    override fun getById(from: LocalDate, to: LocalDate, matchingTargetId: Int): HttpResponse<MatchedTransactions> {
        TODO("Not yet implemented")
    }


}
