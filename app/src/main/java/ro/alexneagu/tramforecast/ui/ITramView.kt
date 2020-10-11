package ro.alexneagu.tramforecast.ui

import ro.alexneagu.tramforecast.model.Direction

interface ITramView {
    fun getMarlboroughTrams()
    fun getStillorganTrams()
    fun onGetAllTramsSuccess(list: List<Direction>, isInbound: Boolean)
    fun onError(message: String?)
    fun showProgress()
    fun hideProgress()
}