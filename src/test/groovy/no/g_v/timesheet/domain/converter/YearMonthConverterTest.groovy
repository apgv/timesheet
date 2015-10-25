package no.g_v.timesheet.domain.converter

import org.junit.Before
import org.junit.Test

import java.sql.Date
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

import static org.junit.Assert.*

class YearMonthConverterTest {

    private YearMonthConverter converter
    private YearMonth yearMonth

    @Before
    public void setUp() throws Exception {
        converter = new YearMonthConverter()
        yearMonth = YearMonth.of(2015, Month.OCTOBER)
    }

    @Test
    public void convertToDatabaseColumn() throws Exception {
        def now = LocalDate.now()
        println "localdate ${now}"
        def date = new Date(Instant.now().toEpochMilli())
        println " sql date ${date}"
        println " sql date 2 ${Date.valueOf(now)}"
        println " new localdate ${date.toLocalDate()}"

        println "${Duration.ofHours(16).plusMinutes(15).minus(Duration.ofHours(8).plusMinutes(5))}"

        assertEquals('2015-10', converter.convertToDatabaseColumn(yearMonth))
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
        assertEquals(yearMonth, converter.convertToEntityAttribute('2015-10'))

    }
}
