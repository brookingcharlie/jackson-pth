package org.example

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class WrapperObjectTest {
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    abstract class AnimalWithWrapperObject

    private val objectMapper = ObjectMapper()
        .addMixIn(Animal::class.java, AnimalWithWrapperObject::class.java)

    @Test
    fun testWrapperObject() {
        val input = """
        {
          "Dog": {
            "name": "Anubis",
            "barkVolume": 120
          }
        }
        """

        val animal = objectMapper.readValue(input, Animal::class.java)
        assertThat(animal.name, CoreMatchers.equalTo("Anubis"))
        assertThat((animal as Dog).barkVolume, CoreMatchers.equalTo(120))
    }
}
