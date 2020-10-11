package ro.alexneagu.tramforecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import ro.alexneagu.tramforecast.R
import ro.alexneagu.tramforecast.model.Direction
import ro.alexneagu.tramforecast.util.Time

class MainActivity : AppCompatActivity(), ITramView {

    private lateinit var presenter: ITramPresenter
    private lateinit var adapter: TramAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        presenter = TramPresenter(this)
        initUI()
    }
    
    fun initUI(){
        adapter = TramAdapter(mutableListOf(), this)
        tramRv.adapter = adapter
        tramRv.layoutManager = LinearLayoutManager(this)

        requestData()

        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmptyAdapter()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                checkEmptyAdapter()
            }
        })
    }

    private fun requestData(){
        if(Time.checkTime()){
            getMarlboroughTrams()
            stationTv.text = "Marlborough"
        }else
        {
            getStillorganTrams()
            stationTv.text = "Stillorgan"
        }
    }

    private fun checkEmptyAdapter() {
        if (adapter.itemCount == 1) { // 1 because of the header, otherwise 0
            Toasty.info(this, "No forecast available. Try to refresh", Toast.LENGTH_LONG, true).show()
        }
    }

    override fun getMarlboroughTrams() {
        presenter.getTramStops("forecast", "mar", false)
    }

    override fun getStillorganTrams() {
        presenter.getTramStops("forecast", "sti", false)
    }

    override fun onGetAllTramsSuccess(list: List<Direction>, isInbound:Boolean) {
        if(isInbound) {
            val name = list[0].name
            if (name == "Inbound") {
                adapter.updateAllTrams(list[0].trams)
                directionTv.text = name
            }
        }else
        {
            val name = list[1].name
            if (name == "Outbound") {
                adapter.updateAllTrams(list[1].trams)
                directionTv.text = name
            }
        }
    }

    override fun onError(message: String?) {
        if (message != null) {
            Toasty.error(this, message, Toast.LENGTH_SHORT, true).show()
            Log.e("ERROR", message)
        }
    }

    override fun showProgress() {
       progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun refresh(view: View) {
        requestData()
    }
}