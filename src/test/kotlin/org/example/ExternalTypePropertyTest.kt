package org.example

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ExternalTypePropertyTest {
    class EnvelopeWithExternalProperty {
        @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.EXTERNAL_PROPERTY, property="type")
        var data: Animal? = null
    }

    private val objectMapper = ObjectMapper()

    @Test
    fun testExternalTypeProperty() {
        val input = """
        {
          "type": "Dog",
          "data": {
            "name": "Anubis",
            "barkVolume": 120
          }
        }
        """

        val envelope = objectMapper.readValue(input, EnvelopeWithExternalProperty::class.java)
        val animal = envelope.data!!
        assertThat(animal.name, CoreMatchers.equalTo("Anubis"))
        assertThat((animal as Dog).barkVolume, CoreMatchers.equalTo(120))
    }
}
