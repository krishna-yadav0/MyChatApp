package com.krishna.mychatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krishna.mychatapp.MessageItemUi.Companion.TYPE_DATE_CONTAINER
import com.krishna.mychatapp.MessageItemUi.Companion.TYPE_FRIEND_MESSAGE
import com.krishna.mychatapp.MessageItemUi.Companion.TYPE_MY_MESSAGE
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a")

//Input to the function
        val messages = arrayListOf<MessageItemUi>(
            MessageItemUi(content = "Hii",TYPE_MY_MESSAGE,formatter.parse("21/10/2012 03:30 pm")),
            MessageItemUi(content = "hello", TYPE_FRIEND_MESSAGE,formatter.parse("22/10/2012 04:32 pm")),
            MessageItemUi(content = "Hii",TYPE_MY_MESSAGE,formatter.parse("22/10/2012 03:30 pm")),
            MessageItemUi(content = "hello", TYPE_FRIEND_MESSAGE,formatter.parse("22/10/2012 04:45 pm")),
            MessageItemUi(content = "Hii",TYPE_MY_MESSAGE,formatter.parse("23/10/2012 03:30 pm")),
            MessageItemUi(content = "hello", TYPE_FRIEND_MESSAGE,formatter.parse("22/10/2012 05:03 pm")),
            MessageItemUi(content = "Hii",TYPE_MY_MESSAGE,formatter.parse("24/10/2012 03:30 pm")),
            MessageItemUi(content = "hello", TYPE_FRIEND_MESSAGE,formatter.parse("22/10/2012 03:10 pm")),
            MessageItemUi(content = "Hii",TYPE_MY_MESSAGE,formatter.parse("25/10/2012 03:30 pm")),
            MessageItemUi(content = "hello", TYPE_FRIEND_MESSAGE,formatter.parse("26/10/2012 02:30 pm")),
        )

        val processMessages = getMessageListWithDate(messages)
        val chats = findViewById<RecyclerView>(R.id.chatItems)
        val adapter = ChatAdapter(processMessages)
        chats.adapter= adapter
        chats.layoutManager = LinearLayoutManager(this)

        val button = findViewById<Button>(R.id.sendButton)
        val messageField = findViewById<EditText>(R.id.messageField)

        button.setOnClickListener {
            val messageValue = messageField.text.toString()
            if (messageValue.isEmpty())
                return@setOnClickListener
            messages.add(MessageItemUi(content = messageValue, TYPE_MY_MESSAGE, Date()))
            adapter.data = getMessageListWithDate(messages)
            messageField.setText("")
            chats.adapter?.notifyDataSetChanged()
        }


    }
    fun getMessageListWithDate(inputList: List<MessageItemUi>):List<MessageItemUi>{
        val sortedList =  inputList.sortedBy { it.date }
        val list = mutableListOf<MessageItemUi>()
        var currentDate:Date? = null
        val formatter = SimpleDateFormat("dd/MM/yy")
        val today = Calendar.getInstance()
        val todayString = formatter.format(today.time)
        today.add(Calendar.DATE,-1)
        val yesterdayString = formatter.format(today.time)
        for (i in sortedList){
            if (i.date != currentDate){
                val dateString = formatter.format(i.date)
                if (dateString == todayString){
                    list.add(MessageItemUi("Today", TYPE_DATE_CONTAINER,i.date))
                }else if (dateString == yesterdayString)
                list.add(MessageItemUi("Yesterday", TYPE_DATE_CONTAINER,i.date))

                else
                    list.add(MessageItemUi(dateString, TYPE_DATE_CONTAINER,i.date))
                currentDate = i.date
            }
            list.add(i)
        }
        return list

    }

}