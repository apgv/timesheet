package no.g_v.timesheet.domain.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Date
import java.time.LocalDate

@Converter(autoApply = true)
class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    Date convertToDatabaseColumn(LocalDate attribute) {
        Date.valueOf(attribute)
    }

    @Override
    LocalDate convertToEntityAttribute(Date dbData) {
        dbData.toLocalDate()
    }
}
