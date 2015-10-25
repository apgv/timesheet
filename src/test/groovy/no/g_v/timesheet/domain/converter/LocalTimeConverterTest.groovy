package no.g_v.timesheet.domain.converter

import org.junit.Before
import org.junit.Test

import java.time.LocalTime

import static org.junit.Assert.assertEquals

class LocalTimeConverterTest {

    private LocalTimeConverter converter
    private LocalTime localTime

    @Before
    public void setUp() throws Exception {
        converter = new LocalTimeConverter()
        localTime = LocalTime.of(7, 30)
    }

    @Test
    public void convertToDatabaseColumn() throws Exception {
        assertEquals('7:30', converter.convertToDatabaseColumn(localTime))
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
        assertEquals(localTime, converter.convertToEntityAttribute('7:30'))

    }
}
