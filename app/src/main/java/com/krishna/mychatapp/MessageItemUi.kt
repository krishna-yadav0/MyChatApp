package com.krishna.mychatapp

import java.text.SimpleDateFormat
import java.util.*

class MessageItemUi (val content:String,val messageType:Int,val date: Date){
    companion object {
        const val TYPE_MY_MESSAGE = 0
        const val TYPE_FRIEND_MESSAGE = 1
        const val TYPE_DATE_CONTAINER = 2

    }


}