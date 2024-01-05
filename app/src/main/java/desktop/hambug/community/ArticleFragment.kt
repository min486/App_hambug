package desktop.hambug.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import desktop.hambug.R
import desktop.hambug.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var binding: FragmentArticleBinding

    private val args: ArticleFragmentArgs by navArgs()  // 네비게이션 argument 사용하기 위해

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        val articleId = args.articleId  // 데이터 찾기

        // 찾은 데이터로 firestore 조회
        Firebase.firestore.collection("articles").document(articleId)
            .get()
            .addOnSuccessListener {
                val model = it.toObject<ArticleModel>()
                binding.titleTextView.text = model?.title
                binding.descriptionTextView.text = model?.description

                Glide.with(binding.photoImageView)
                    .load(model?.imageUri)
                    .into(binding.photoImageView)
            }.addOnFailureListener {

            }

        binding.backImageView.setOnClickListener {
            findNavController().navigate(ArticleFragmentDirections.actionArticleFragmentToCommunityFragment())
        }
    }
}