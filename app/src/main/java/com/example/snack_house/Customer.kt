package com.example.snack_house

class Customer private constructor(var firstName: String, var lastName: String, var emailAddress: String, var phone: String, var address: String, city: String, state: String, zip: String) {
    var city: String? = null
    var state: String? = null
    var zip: String? = null

}