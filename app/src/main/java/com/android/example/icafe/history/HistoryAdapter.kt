package com.android.example.icafe.history

import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.icafe.R
import com.android.example.icafe.database.DataHistory

class HistoryAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data =  listOf<DataHistory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {

        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
        val item = data[position]

        Log.i("ShowHS", "${item.name}")

        holder.textView.text = "\t${item.name}(${item.age})  Total ${item.cost} Baht. \n"
        holder.textView.setTextColor(Color.WHITE)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.history_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)