package com.example.portableinventory.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "product")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    var name: String?,
    var quantity: Int? = 0,
    var expiryDate: String?,
    var imgName: String?,
    var recipe: String?,
    var category: String?
) : Parcelable {
    companion object {
        const val TAG = "product"
    }
}
