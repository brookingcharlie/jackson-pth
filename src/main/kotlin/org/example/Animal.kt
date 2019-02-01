package org.example

import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonSubTypes(JsonSubTypes.Type(value = Dog::class, name = "Dog"))
open class Animal protected constructor() {
    var name: String? = null
}

class Dog : Animal() {
    var barkVolume: Int = 0
}
