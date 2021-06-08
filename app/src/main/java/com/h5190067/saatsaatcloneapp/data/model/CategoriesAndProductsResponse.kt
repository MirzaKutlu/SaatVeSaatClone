package com.h5190067.saatsaatcloneapp.data.model

class CategoriesAndProductsResponse : ArrayList<CategoriesAndProductsResponseItem>()

data class CategoriesAndProductsResponseItem(
    val KategoriAdi: String?,
    val Products: List<Product>?,
    val kapakResim: String?
)

data class Product(
    val Aciklama: String?,
    val Fiyat: String?,
    val KasaBoyutu: String?,
    val KasaTipi: String?,
    val KategoriAdi: String?,
    val MalzemeTipi: String?,
    val MarkaAdi: String?,
    val Renk: String?,
    val Sertifika: String?,
    val UrunResim: String?
)