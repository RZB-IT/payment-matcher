package cz.teddy.matcher.service

import cz.teddy.matcher.client.TransactionClient
import cz.teddy.matcher.jpa.repository.MatchedTransactionRepository
import cz.teddy.matcher.jpa.repository.TransactionRepository
import cz.teddy.matcher.mapper.TransactionMapper
import jakarta.annotation.PostConstruct
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking

@Singleton
class TransactionService(
   val transactionClient: TransactionClient,
   val transactionRepository: TransactionRepository,
   val transactionMapper: TransactionMapper,
) {
    @PostConstruct
    fun postConstruct(){
        load()
    }
    fun load() = runBlocking{
       for (transactionDto in transactionClient.fetchTransactions(null,null ,null)){
           if (transactionRepository.findByTransactionId(transactionDto.transactionId).isEmpty) {
               transactionRepository.save(transactionMapper.mapDtoToEntity(transactionDto))
           }
       }
    }
}