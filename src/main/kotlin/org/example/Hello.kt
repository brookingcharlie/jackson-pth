package org.example

import com.fasterxml.jackson.annotation.JsonTypeInfo

class EnvelopeWithInternalProperty {
    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@type")
    var data: Animal? = null
}

class EnvelopeWithWrapperObject {
    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    var data: Animal? = null
}

class EnvelopeWithExternalProperty {
    @JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.EXTERNAL_PROPERTY, property="type")
    var data: Animal? = null
}

open class Animal protected constructor() {
    var name: String? = null
}

class Dog : Animal() {
    var barkVolume: Int = 0
}
