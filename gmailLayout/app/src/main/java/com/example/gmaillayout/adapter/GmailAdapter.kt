package com.example.gmaillayout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gmaillayout.R
import com.example.gmaillayout.adapter.GmailAdapter.GmailViewHolder
import com.example.gmaillayout.modle.Gmail

class GmailAdapter(private val myGmailList: List<Gmail>?) : RecyclerView.Adapter<GmailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return GmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: GmailViewHolder, position: Int) {
        val gmail = myGmailList!![position] ?: return
        holder.senderName.text = gmail.senderName
        holder.time.text = gmail.time
        holder.description.text = gmail.description
        holder.avatar.setImageResource(gmail.avatar)
    }

    override fun getItemCount(): Int {
        if (myGmailList != null) {
            return myGmailList.size
        }
        return 0
    }

    inner class GmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val senderName: TextView = itemView.findViewById(R.id.senderName)
        val time: TextView = itemView.findViewById(R.id.time)
        val description: TextView = itemView.findViewById(R.id.description)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
    }
}