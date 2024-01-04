package desktop.hambug.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import desktop.hambug.R
import desktop.hambug.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment(R.layout.fragment_community) {

    private lateinit var binding: FragmentCommunityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommunityBinding.bind(view)

        // val db = Firebase.firestore

        setupWriteButton(view)
    }

    private fun setupWriteButton(view: View) {
        binding.writeButton.setOnClickListener {
            //if (Firebase.auth.currentUser != null) {
                val action = CommunityFragmentDirections.actionCommunityFragmentToWriteArticleFragment()
                findNavController().navigate(action)
            //} else {
                //Snackbar.make(view, "로그인 후 사용 가능합니다", Snackbar.LENGTH_SHORT).show()
            //}
        }
    }

}