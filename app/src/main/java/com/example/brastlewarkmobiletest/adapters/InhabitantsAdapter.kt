package com.example.brastlewarkmobiletest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.brastlewarkmobiletest.common.MyApp
import com.example.brastlewarkmobiletest.common.Utils
import com.example.brastlewarkmobiletest.databinding.ItemInhabitantBinding
import com.example.brastlewarkmobiletest.domain.Inhabitant
import java.net.URL

class InhabitantsAdapter(private var inhabitantsList: ArrayList<Inhabitant>, private val onInhabitantSelected: (inhabitant: Inhabitant) -> Unit) : RecyclerView.Adapter<InhabitantsAdapter.ViewHolder>(), Filterable {

    var inhabitantsFilterList = ArrayList<Inhabitant>()

    class ViewHolder(val binding: ItemInhabitantBinding) : RecyclerView.ViewHolder(binding.root)

    init {
        inhabitantsFilterList = inhabitantsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInhabitantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inhabitant = inhabitantsFilterList[position]

        with(holder) {

            Glide.with(MyApp.applicationContext()).load(Utils().generateGlideUrl(inhabitant.thumbnail)).into(binding.imageViewInhabitant)

            binding.txtInhabitantName.text = inhabitant.name
            binding.txtInhabitantAge.text = inhabitant.age.toString()
            binding.textViewHeight.text = inhabitant.height.toString()
            binding.textViewWeight.text = inhabitant.weight.toString()

            itemView.setOnClickListener { onInhabitantSelected.invoke(inhabitant) }
        }
    }

    override fun getItemCount(): Int {
        return inhabitantsFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraints: CharSequence?): FilterResults {
                val charSearch = constraints.toString()
                inhabitantsFilterList = if(charSearch.isEmpty()) {
                    inhabitantsList
                } else {
                    val resultList = ArrayList<Inhabitant>()
                    for(row in inhabitantsList) {
                        if(row.name.contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = inhabitantsFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraints: CharSequence?, results: FilterResults?) {
                inhabitantsFilterList = results?.values as ArrayList<Inhabitant>
                notifyDataSetChanged()
            }
        }
    }
}