package ro.alexneagu.tramforecast.repository


import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ro.alexneagu.tramforecast.model.Direction
import ro.alexneagu.tramforecast.network.RetrofitAPI

object Repository {
    private val retrofitClient by lazy { RetrofitAPI.retrofitClient() }

    fun getTramStops(action: String, stop: String, encrypt: Boolean): Single<List<Direction>> {
        return retrofitClient.getTramStops(action, stop, encrypt)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.direction
            }
    }


}