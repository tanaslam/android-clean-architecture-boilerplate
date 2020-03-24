package org.healthapi.android.boilerplate.ui.browse

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.healthapi.android.boilerplate.ui.R
import org.healthapi.android.boilerplate.ui.model.Covid19StatsViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ItemViewHolder>() {

    var covid19Stats: List<Covid19StatsViewModel> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.takeIf { it is HeaderViewHolder }?.apply {
            tvCountry.text = holder.itemView.context.getText(R.string.header_country)
            tvCountryCode.text = holder.itemView.context.getText(R.string.header_cc)
            tvConfirmed.text = holder.itemView.context.getText(R.string.header_confirmed)
            tvDeaths.text = holder.itemView.context.getText(R.string.header_deaths)
            tvRecovered.text = holder.itemView.context.getText(R.string.header_recover)
        } ?: holder.apply {

            val item = covid19Stats[position - 1]

            tvCountry.text = item.country.capitalize()
            tvCountryCode.text = item.countryCode
            tvConfirmed.text = "${item.confirmed}"
            tvDeaths.text = "${item.deaths}"
            tvRecovered.text = "${item.recovered}"
        }

        /*
        Glide.with(holder.itemView.context)
                .load(item.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = View.inflate(parent.context, R.layout.item_covid19_stats, null)
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(itemView)
            else -> ItemViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return covid19Stats.size + 1
    }

    open inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvCountry: TextView = view.findViewById(R.id.text_coutry)
        var tvCountryCode: TextView = view.findViewById(R.id.text_cc)
        var tvConfirmed: TextView = view.findViewById(R.id.text_confirmed)
        var tvDeaths: TextView = view.findViewById(R.id.text_deaths)
        var tvRecovered: TextView = view.findViewById(R.id.text_recorvered)

    }

    inner class HeaderViewHolder(view: View) : ItemViewHolder(view)

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

}