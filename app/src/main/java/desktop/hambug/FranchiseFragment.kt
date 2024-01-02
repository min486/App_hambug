package desktop.hambug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import desktop.hambug.databinding.FragmentFranchiseBinding

class FranchiseFragment: Fragment(R.layout.fragment_franchise) {
    private lateinit var binding: FragmentFranchiseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFranchiseBinding.bind(view)
    }
}