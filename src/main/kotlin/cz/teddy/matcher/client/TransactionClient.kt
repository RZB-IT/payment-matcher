package cz.teddy.matcher.client
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import io.micronaut.serde.annotation.SerdeImport

@SerdeImport(value = TransactionDTO::class)
@Client(id = "bank-service")
interface TransactionClient {
    @Get("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    suspend fun fetchTransactions(
        @QueryValue query: String?,
        @QueryValue limit: String?,
        @QueryValue offset: Int?,
    ): List<TransactionDTO>
}
