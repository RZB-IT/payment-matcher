package cz.teddy.matcher.mapper

import cz.teddy.matcher.client.TransactionDTO
import cz.teddy.matcher.service.domain.MatchingTarget
import cz.teddy.matcher.service.domain.Transaction
import cz.teddy.matcher.jpa.entity.MatchingTargetEntity
import cz.teddy.matcher.jpa.entity.TransactionEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330",
    uses = [MapperUtils::class])
interface TransactionMapper {
//    fun mapDtoToService(matchingTargetDTO: MatchingTargetDTO): MatchingTarget

    fun mapEntityToService(transactionEntity: TransactionEntity): Transaction

    fun mapServiceToEntity(transaction: Transaction): TransactionEntity

    fun mapServiceToDto(transaction: Transaction): TransactionDTO

    fun mapDtoToEntity(transactionDTO: TransactionDTO) : TransactionEntity
}