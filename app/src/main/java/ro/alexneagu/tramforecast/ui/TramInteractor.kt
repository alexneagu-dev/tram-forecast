package ro.alexneagu.tramforecast.ui

import android.annotation.SuppressLint
import ro.alexneagu.tramforecast.repository.Repository

class TramInteractor(private val presenter: ITramPresenter) : ITramInteractor {
    @SuppressLint("CheckResult")
    override fun getTramStops(action: String, stop: String, encrypt: Boolean) {
        Repository.getTramStops(action, stop, encrypt)
            .subscribe({ list ->
                presenter.onGetAllTramsSuccess(list, false)
            }, { error -> presenter.onError(error)
            })
    }

}