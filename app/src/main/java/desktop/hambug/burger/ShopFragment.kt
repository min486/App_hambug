package desktop.hambug.burger

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import desktop.hambug.R
import desktop.hambug.databinding.FragmentShopBinding
import java.io.IOException

class ShopFragment: Fragment(R.layout.fragment_shop) {
    private lateinit var binding: FragmentShopBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopBinding.bind(view)

        val data = getJsonData("shop_list.json")

        binding.shopRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ShopAdapter(data!!)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun getJsonData(filename: String): Shop? {
        val assetManager = resources.assets
        var result: Shop? = null
        try {
            val inputStream = assetManager.open(filename)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, Shop::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

}