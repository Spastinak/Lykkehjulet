package com.example.lykkehjulet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the recycler view
 * the recycler view represents the lives
 */
class LivesAdapter(private val lives: List<String>) : RecyclerView.Adapter<LivesAdapter.ViewHolder>() {
    private var livesCopy = lives

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.HeartTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lives,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = livesCopy[position]
    }

    override fun getItemCount(): Int {
        return lives.size
    }

}