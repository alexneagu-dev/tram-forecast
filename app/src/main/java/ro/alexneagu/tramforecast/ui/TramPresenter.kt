package ro.alexneagu.tramforecast.ui

import ro.alexneagu.tramforecast.model.Direction


class TramPresenter(private val view: ITramView): ITramPresenter {
    private val interactor: ITramInteractor = TramInteractor(this)

    override fun getTramStops(action: String, stop: String, encrypt: Boolean) {
        view.showProgress()
        interactor.getTramStops(action, stop, encrypt)
    }

    override fun onGetAllTramsSuccess(list: List<Direction>, isInbound: Boolean) {
        view.hideProgress()
        view.onGetAllTramsSuccess(list, isInbound)
    }

    override fun onError(error: Throwable) {
        view.hideProgress()
        view.onError(error.message)
    }

}