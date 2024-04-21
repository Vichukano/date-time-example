package ru.vichukano.datatimeexample

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

@SpringBootTest
class DataTimeExampleApplicationTests {
    @Autowired
    private lateinit var repository: TestTableRepository

    @Test
    fun `should extract date time with same offset`() {
        val moscowOffset = ZoneOffset.ofHours(3)
        val moscowNow = OffsetDateTime.of(LocalDateTime.now(), moscowOffset)
        val record = TestTableEntity(
                dateTime = moscowNow
        )

        repository.save(record)
        repository.flush()
        val extractedRecord = repository.findById(record.id!!).get()

        Assertions.assertEquals(moscowOffset, moscowNow.offset)
        Assertions.assertEquals(moscowNow, extractedRecord.dateTime)
    }

}
