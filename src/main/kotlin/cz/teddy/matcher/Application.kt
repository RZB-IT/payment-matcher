package cz.teddy.matcher

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*



object Api {
}
fun main(args: Array<String>) {
    Micronaut.run(Api.javaClass)
}

