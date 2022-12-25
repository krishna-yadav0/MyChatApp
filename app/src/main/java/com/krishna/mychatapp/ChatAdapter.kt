package com.krishna.mychatapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krishna.mychatapp.MessageItemUi.Companion.TYPE_DATE_CONTAINER
import com.krishna.mychatapp.MessageItemUi.Companion.TYPE_FRIEND_MESSAGE
import com.krishna.mychatapp.MessageItemUi.Companion.TYPE_MY_MESSAGE
import java.text.SimpleDateFormat


class ChatAdapter(var data:List<MessageItemUi>):RecyclerView.Adapter<MessageViewHolder<MessageItemUi>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder<MessageItemUi> {
        val context = parent.context
        return when (viewType) {
            TYPE_MY_MESSAGE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.my_message_item, parent, false)
                MyMessageViewHolder(view)
            }
            TYPE_FRIEND_MESSAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_message_item, parent, false)
                FriendMessageViewHolder(view)
            }
            TYPE_DATE_CONTAINER ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.date_item, parent, false)
                DateViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: MessageViewHolder<MessageItemUi>,position: Int) {
        val item = data[position]
        Log.d("adapter View", position.toString() + item.content + item.date)
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].messageType

    class MyMessageViewHolder(val view: View): MessageViewHolder<MessageItemUi>(view){
        private val messageContent = view.findViewById<TextView>(R.id.message)
        private val time = view.findViewById<TextView>(R.id.time)
        val formatter = SimpleDateFormat("hh:mm a")

        override fun bind(item: MessageItemUi) {
            messageContent.text = item.content
            time.text=formatter.format(item.date)

        }
    }

    class FriendMessageViewHolder(val view: View): MessageViewHolder<MessageItemUi>(view) {
        private val messageContent = view.findViewById<TextView>(R.id.message)
        private val time = view.findViewById<TextView>(R.id.time)
        val formatter = SimpleDateFormat("hh:mm a")

        override fun bind(item: MessageItemUi) {
            messageContent.text = item.content
            time.text=formatter.format(item.date)

        }
    }

    class DateViewHolder(val view: View):MessageViewHolder<MessageItemUi>(view){
        private val dateContent = view.findViewById<TextView>(R.id.date)

        override fun bind(item: MessageItemUi) {
            dateContent.text = item.content
        }
    }
}