package no.g_v.timesheet.domain.converter

import org.junit.Before
import org.junit.Test

import java.sql.Date
import java.time.LocalDate
import java.time.Month

import static org.junit.Assert.*

class LocalDateConverterTest {

    private LocalDateConverter converter
    private LocalDate localDate
    private Date sqlDate

    @Before
    public void setUp() throws Exception {
        converter = new LocalDateConverter()
        localDate = LocalDate.of(2015, Month.OCTOBER, 25)
        sqlDate = Date.valueOf(localDate)
    }

    @Test
    public void convertToDatabaseColumn() throws Exception {
        assertEquals(sqlDate, converter.convertToDatabaseColumn(localDate))
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
        assertEquals(localDate, converter.convertToEntityAttribute(sqlDate))
    }
}
