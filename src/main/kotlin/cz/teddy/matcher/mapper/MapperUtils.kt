package cz.teddy.matcher.mapper

import jakarta.inject.Singleton
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Singleton
class MapperUtils {
    fun map(timestamp: Timestamp): LocalDateTime {
       return LocalDateTime.from(timestamp.toLocalDateTime())
    }

    fun map(localDateTime: LocalDateTime) : Timestamp {
        return Timestamp.from(localDateTime.toInstant(ZoneOffset.UTC))
    }
}