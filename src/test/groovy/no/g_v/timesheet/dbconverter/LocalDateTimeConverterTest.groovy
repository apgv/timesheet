package no.g_v.timesheet.dbconverter

import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class LocalDateTimeConverterTest extends Specification {

    def "convert java.time.LocalDateTime to java.sql.Timestamp"() {
        given:
        def converter = new LocalDateTimeConverter()
        def localDateTime = LocalDateTime.of(2015, 1, 1, 12, 12, 12)

        when:
        def databaseColumn = converter.convertToDatabaseColumn(localDateTime)

        then:
        databaseColumn == new Timestamp(115, 0, 1, 12, 12, 12, 0)
    }

    def "convert java.sql.Timestamp to java.time.LocalDateTime"() {
        given:
        def converter = new LocalDateTimeConverter()
        def timestamp = new Timestamp(115, 0, 1, 12, 12, 12, 0)

        when:
        def entityAttribute = converter.convertToEntityAttribute(timestamp)

        then:
        entityAttribute == LocalDateTime.of(2015, 1, 1, 12, 12, 12)
    }
}
