package manabboro.alarmclock.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import manabboro.alarmclock.R
import manabboro.alarmclock.model.Alarm

class MainAdapter constructor(context: Context) :
    RecyclerView.Adapter<MainViewHolder>() {

    private val mInflater = LayoutInflater.from(context)
    private var mData = listOf<Alarm>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = mInflater.inflate(R.layout.single_main_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun updateData(list: List<Alarm>) {
        mData = list
        notifyDataSetChanged()
    }
}