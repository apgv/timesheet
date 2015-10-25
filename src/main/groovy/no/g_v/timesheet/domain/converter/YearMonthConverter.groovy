package no.g_v.timesheet.domain.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.time.YearMonth

@Converter(autoApply = true)
class YearMonthConverter implements AttributeConverter<YearMonth, String> {
    @Override
    String convertToDatabaseColumn(YearMonth attribute) {
        attribute.toString()
    }

    @Override
    YearMonth convertToEntityAttribute(String dbData) {
        def arrayOfStrings = dbData.split('-')
        YearMonth.of(arrayOfStrings[0] as Integer, arrayOfStrings[1] as Integer)
    }
}
