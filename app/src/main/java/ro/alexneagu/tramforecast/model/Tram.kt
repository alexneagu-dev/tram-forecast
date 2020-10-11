package ro.alexneagu.tramforecast.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root


@Root(name = "tram", strict = false)
data class Tram @JvmOverloads constructor(
    @field: Attribute(name = "dueMins")
    var dueMins: String = "",
    @field: Attribute(name = "destination")
    var destination: String = ""
)