package desktop.hambug.burger

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import desktop.hambug.burger.BurgersFragment
import desktop.hambug.burger.FranchiseFragment
import desktop.hambug.burger.ShopFragment

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