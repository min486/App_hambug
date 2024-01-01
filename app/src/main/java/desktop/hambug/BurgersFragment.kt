package desktop.hambug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import desktop.hambug.databinding.FragmentBurgersBinding

class BurgersFragment : Fragment(R.layout.fragment_burgers) {

    private lateinit var binding: FragmentBurgersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBurgersBinding.bind(view)
    }


}