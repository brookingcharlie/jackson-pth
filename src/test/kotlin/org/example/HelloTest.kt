package org.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HelloTest {
    @Test
    fun testInternalTypeProperty() {
        val input = """
        {
          "data": {
            "@type": "org.example.Dog",
            "name": "Anubis",
            "barkVolume": 120
          }
        }
        """
        val objectMapper = ObjectMapper()
        val envelope = objectMapper.readValue(input, EnvelopeWithInternalProperty::class.java)
        assertThat(envelope.data?.name, CoreMatchers.equalTo("Anubis"))
        assertThat((envelope.data as Dog).barkVolume, CoreMatchers.equalTo(120))
    }

    @Test
    fun testWrapperObject() {
        val input = """
        {
          "data": {
            "org.example.Dog": {
              "name": "Anubis",
              "barkVolume": 120
            }
          }
        }
        """
        val objectMapper = ObjectMapper()
        val envelope = objectMapper.readValue(input, EnvelopeWithWrapperObject::class.java)
        assertThat(envelope.data?.name, CoreMatchers.equalTo("Anubis"))
        assertThat((envelope.data as Dog).barkVolume, CoreMatchers.equalTo(120))
    }

    @Test
    fun testExternalTypeProperty() {
        val input = """
        {
          "type": "org.example.Dog",
          "data": {
            "name": "Anubis",
            "barkVolume": 120
          }
        }
        """
        val objectMapper = ObjectMapper()
        val envelope = objectMapper.readValue(input, EnvelopeWithExternalProperty::class.java)
        assertThat(envelope.data?.name, CoreMatchers.equalTo("Anubis"))
        assertThat((envelope.data as Dog).barkVolume, CoreMatchers.equalTo(120))
    }
}
