package org.example

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class InternalTypePropertyTest {
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="@type")
    abstract class AnimalWithInternalTypeProperty

    private val objectMapper = ObjectMapper()
        .addMixIn(Animal::class.java, AnimalWithInternalTypeProperty::class.java)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @Test
    fun testInternalTypeProperty() {
        val input = """
        {
          "@type": "Dog",
          "name": "Anubis",
          "barkVolume": 120
        }
        """

        val animal = objectMapper.readValue(input, Animal::class.java)
        assertThat(animal.name, CoreMatchers.equalTo("Anubis"))
        assertThat((animal as Dog).barkVolume, CoreMatchers.equalTo(120))
    }
}
