package no.g_v.timesheet.dbconverter

import spock.lang.Specification

import java.sql.Date
import java.time.LocalDate
import java.time.YearMonth


class YearMonthConverterTest extends Specification {

    def "convert java.time.YearMonth to java.sql.Date"() {
        given:
        def converter = new YearMonthConverter()
        def yearMonth = YearMonth.of(2015, 1)

        when:
        def databaseColumn = converter.convertToDatabaseColumn(yearMonth)

        then:
        databaseColumn == new Date(115, 0, 1)
    }

    def "convert java.sql.Date to java.time.YearMonth"() {
        given:
        def converter = new YearMonthConverter()
        def sqlDate = new Date(115, 0, 1)

        when:
        def entityAttribute = converter.convertToEntityAttribute(sqlDate)

        then:
        entityAttribute == YearMonth.of(2015, 1)
    }
}
