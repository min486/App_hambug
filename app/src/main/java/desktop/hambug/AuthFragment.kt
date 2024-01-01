package desktop.hambug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import desktop.hambug.databinding.FragmentAuthBinding

class AuthFragment: Fragment(R.layout.fragment_auth) {

    private lateinit var binding : FragmentAuthBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)

        binding.moveButton.setOnClickListener {


//            val action = AuthFragmentDirections.actionAuthFragmentToHomeFragment()
//            findNavController().navigate(action)

        }
    }
}