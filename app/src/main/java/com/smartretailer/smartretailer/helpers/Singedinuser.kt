package com.smartretailer.smartretailer.helpers

class Singedinuser {
    companion object{
        lateinit var email:String
        lateinit var refreshtoken:String
        lateinit var idtoken :String
        lateinit var userid :String
        fun getuserid(): String? {
            if(this::userid.isInitialized)
                return userid
            else
                return null
        }
    }
}