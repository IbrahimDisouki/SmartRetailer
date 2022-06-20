package com.smartretailer.smartretailer.helpers

data class Refreshtoken(val expires_in:String, val token_type:String, val refresh_token:String, val id_token:String, val user_id:String, val project_id:String)
