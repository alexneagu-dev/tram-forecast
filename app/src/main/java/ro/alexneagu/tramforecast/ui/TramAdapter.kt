package ro.alexneagu.tramforecast.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tram_list_item.view.*
import ro.alexneagu.tramforecast.R
import ro.alexneagu.tramforecast.model.Tram


class TramAdapter(private var items: List<Tram>,
                  private val context: Context):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER : Int = 0
    private val TYPE_LIST : Int = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == TYPE_HEADER) {
            val header = LayoutInflater.from(parent.context).inflate(R.layout.tram_list_header,parent,false)
            HeaderViewHolder(header)
        }else {
            val header =
                LayoutInflater.from(context).inflate(R.layout.tram_list_item, parent, false)
            TramViewHolder(header)
        }
    }

    override fun getItemCount(): Int = items.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TramViewHolder) {
            holder.destinationTv.text = getItem(position)?.destination
            holder.dueMinsTv.text = getItem(position)?.dueMins
        } else if (holder is HeaderViewHolder) {
            holder.destinationTv.text = "Destination"
            holder.dueMinsTv.text = "Due Min."
        }

    }

    override fun getItemViewType(position: Int): Int {

        if(isPositionHeader(position))
        {
            return TYPE_HEADER
        }
        return TYPE_LIST
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    private fun getItem(position: Int): Tram? {
        return items[position - 1]
    }

    fun updateAllTrams(list: List<Tram>?) {
        if (list != null) {
            items = list
        }
        notifyDataSetChanged()
    }

    class TramViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val destinationTv: TextView = view.destinationTv
        val dueMinsTv: TextView = view.dueMinsTv
    }

    class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val destinationTv: TextView = view.destinationTv
        val dueMinsTv: TextView = view.dueMinsTv
    }
}