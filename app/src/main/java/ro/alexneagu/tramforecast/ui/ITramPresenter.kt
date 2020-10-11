package ro.alexneagu.tramforecast.ui

import ro.alexneagu.tramforecast.model.Direction


interface ITramPresenter {
    fun getTramStops(action: String, stop: String, encrypt: Boolean)
    fun onGetAllTramsSuccess(list: List<Direction>, isInbound: Boolean)
    fun onError(error: Throwable)
}