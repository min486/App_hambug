package desktop.hambug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import desktop.hambug.databinding.FragmentBurgersBinding

class BurgersFragment : Fragment(R.layout.fragment_burgers) {

    private lateinit var binding: FragmentBurgersBinding
    private val tabText = listOf("프랜차이즈", "수제버거")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBurgersBinding.bind(view)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        // tabLayout과 viewPager 결합
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                tab.text = tabText[position]
            }
        }.attach()
    }

}