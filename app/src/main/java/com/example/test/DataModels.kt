package com.example.test

data class PostModel(
    var name : String? =null ,
    var email : String?=null,
    var password : String? =null
)

data class PostResult(
    var result:String? = null
)
