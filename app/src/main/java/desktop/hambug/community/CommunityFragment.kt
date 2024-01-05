package desktop.hambug.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import desktop.hambug.R
import desktop.hambug.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment(R.layout.fragment_community) {

    private lateinit var binding: FragmentCommunityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommunityBinding.bind(view)

        setupWriteButton(view)

        val articleAdapter = ArticleAdapter {
            // 해당 article 눌렀을때
            findNavController().navigate(CommunityFragmentDirections.actionCommunityFragmentToArticleFragment(
                articleId = it.articleId.orEmpty()  // 데이터 넣어주기
            ))
        }

        binding.communityRecycleView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = articleAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        // firestore에서 데이터 받아오기
        Firebase.firestore.collection("articles").orderBy("createdAt", Query.Direction.DESCENDING)  // 생성시간 내림차순
            .get()
            .addOnSuccessListener { result ->

                // 데이터 하나씩 가져오기
                val list = result.map {
                    it.toObject<ArticleModel>()  // 데이터는 ArticleModel로 매핑
                }

                articleAdapter.submitList(list)  // 데이터들 넣어주기
            }
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