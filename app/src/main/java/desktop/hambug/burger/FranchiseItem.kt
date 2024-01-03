package desktop.hambug.burger

import com.google.gson.annotations.SerializedName

data class FranchiseItem(
    @SerializedName("type")
    val type: String,
    @SerializedName("shopId")
    val shopId: String,
    @SerializedName("shopName")
    val shopName: String,
    @SerializedName("shopNumber")
    val shopNumber: String,
)
