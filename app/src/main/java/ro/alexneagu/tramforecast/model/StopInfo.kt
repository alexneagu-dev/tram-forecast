package ro.alexneagu.tramforecast.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Response")
data class StopInfo @JvmOverloads constructor(
    @field: Attribute(name = "created")
    var created: String = "",
    @field: Attribute(name = "stop")
    var stop: String = "",
    @field: Attribute(name = "stopAbv")
    var stopAbv: String = "",
    @field: Element(name = "message")
    var message: String = "",
    @field:ElementList(name = "direction", inline = true)
    var direction: List<Direction>? = null
)