package desktop.hambug.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import desktop.hambug.R
import desktop.hambug.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment(R.layout.fragment_community) {

    private lateinit var binding: FragmentCommunityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommunityBinding.bind(view)
    }
}