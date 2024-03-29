package com.meals.data.model

import com.google.gson.annotations.SerializedName


data class CategoryDto(
    @SerializedName("idCategory")
    val categoryID: String,
    @SerializedName("strCategory")
    val categoryName: String,
    @SerializedName("strCategoryThumb")
    val categoryThumb: String
)
