package com.h5190067.saatsaatcloneapp.data.model

data class CategoryProductResponse(
    val Categories: List<Category>,
    val Products: List<Product>
)

data class Category(
    val KategoriAdi: String,
    val kapakResim: String
)

data class Product(
    val Aciklama: String,
    val Fiyat: String,
    val KasaBoyutu: String,
    val KasaTipi: String,
    val KategoriAdi: String,
    val MalzemeTipi: String,
    val MarkaAdi: String,
    val Renk: String,
    val Sertifika: String,
    val UrunResim: String
)