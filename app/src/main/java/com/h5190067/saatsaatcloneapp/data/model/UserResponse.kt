package com.h5190067.saatsaatcloneapp.data.model

data class UserResponse(
    val Kullanicilar: List<Kullanicilar>
)

data class Kullanicilar(
    val AdiSoyadi: String,
    val Email: String,
    val Sifre: String
)