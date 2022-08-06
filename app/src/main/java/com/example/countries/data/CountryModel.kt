package com.example.countries.data

import com.google.gson.annotations.SerializedName

data class CountryModel (
    @SerializedName("data") val data : List<Data>,
    @SerializedName("links") val links : List<Links>,
)
data class Data (
    @SerializedName("code") val code : String,
    @SerializedName("currencyCodes") val currencyCodes : List<String>,
    @SerializedName("name") val name : String,
    @SerializedName("wikiDataId") val wikiDataId : String,
    @SerializedName("flagImageUri") val flagImageUri: String
)
data class Links (
    @SerializedName("rel") val rel : String,
    @SerializedName("href") val href : String
)
