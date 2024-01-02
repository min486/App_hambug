package desktop.hambug

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val burgersFragment: BurgersFragment) : FragmentStateAdapter(burgersFragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                return FranchiseFragment()
            }
            else -> {
                return ShopFragment()
            }
        }
    }

}