package desktop.hambug.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import desktop.hambug.R
import desktop.hambug.databinding.FragmentMyBinding

class MyFragment : Fragment(R.layout.fragment_my) {

    private lateinit var binding: FragmentMyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyBinding.bind(view)
    }
}