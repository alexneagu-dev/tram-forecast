package ro.alexneagu.tramforecast.ui

interface ITramInteractor {
    fun getTramStops(action: String, stop: String, encrypt: Boolean)
}