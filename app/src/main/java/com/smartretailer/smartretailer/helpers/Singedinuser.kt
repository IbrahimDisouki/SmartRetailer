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

        fun delete() {
            if(this::email.isInitialized)
                email="null"
            if(this::refreshtoken.isInitialized)
                refreshtoken="null"
            if(this::idtoken.isInitialized)
                idtoken="null"
            if(this::userid.isInitialized)
                userid="null"
        }

        fun isvalid(): Boolean {
            return if(this::email.isInitialized&&this::refreshtoken.isInitialized&&this::idtoken.isInitialized&&this::userid.isInitialized) {
                !(email=="null"||refreshtoken=="null"||idtoken=="null"||userid=="null")
            } else
                false
        }
    }
}