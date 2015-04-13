package no.g_v.timesheet.dbconverter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Timestamp
import java.time.LocalDateTime

@Converter(autoApply = true)
class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        Timestamp.valueOf(attribute)
    }

    @Override
    LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        dbData.toLocalDateTime()
    }
}
