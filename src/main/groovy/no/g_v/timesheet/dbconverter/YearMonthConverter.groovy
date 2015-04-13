package no.g_v.timesheet.dbconverter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Date
import java.time.LocalDate
import java.time.YearMonth

@Converter(autoApply = true)
class YearMonthConverter implements AttributeConverter<YearMonth, Date> {

    @Override
    Date convertToDatabaseColumn(YearMonth attribute) {
        Date.valueOf(LocalDate.of(attribute.year, attribute.month, 1))
    }

    @Override
    YearMonth convertToEntityAttribute(Date dbData) {
        YearMonth.from(dbData.toLocalDate())
    }
}
