package no.g_v.timesheet.domain.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.time.LocalTime

@Converter(autoApply = true)
class LocalTimeConverter implements AttributeConverter<LocalTime, String> {
    @Override
    String convertToDatabaseColumn(LocalTime attribute) {
        "${attribute.getHour()}:${attribute.getMinute()}"
    }

    @Override
    LocalTime convertToEntityAttribute(String dbData) {
        def arrayOfStrings = dbData.split(':')
        LocalTime.of(arrayOfStrings[0] as Integer, arrayOfStrings[1] as Integer)
    }
}
