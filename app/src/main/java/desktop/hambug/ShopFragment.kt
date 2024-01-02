package desktop.hambug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import desktop.hambug.databinding.FragmentShopBinding

class ShopFragment: Fragment(R.layout.fragment_shop) {
    private lateinit var binding: FragmentShopBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopBinding.bind(view)
    }
}