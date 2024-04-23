package cz.teddy.matcher.mapper

import cz.teddy.matcher.controller.dto.MatchedTransactionsDTO
import cz.teddy.matcher.service.domain.MatchedTransaction
import cz.teddy.matcher.jpa.entity.MatchedTransactionEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "jsr330",
    uses = [MapperUtils::class, MatchingTargetMapper::class])
interface MatchedTransactionMapper {
//    fun mapDtoToService(matchingTargetDTO: MatchedTransactionsDTO): MatchedTransaction

    fun mapEntityToService(matchedTransactionEntity: MatchedTransactionEntity): MatchedTransaction

    fun mapServiceToEntity(matchedTransaction: MatchedTransaction): MatchedTransactionEntity

    @Mapping(source = "transaction.transactionId", target = "transactionId" )
    @Mapping(source = "transaction.amount", target = "transactionAmount" )
    @Mapping(source = "target.identifier", target = "identifier" )
    fun mapServiceToDto(matchedTransaction: MatchedTransaction): MatchedTransactionsDTO

}