package desktop.hambug.burger

import com.google.gson.annotations.SerializedName

data class ShopItem(
    @SerializedName("type")
    val type: String,
    @SerializedName("shopId")
    val shopId: String,
    @SerializedName("shopName")
    val shopName: String,
    @SerializedName("shopAddress")
    val shopAddress: String,
)
