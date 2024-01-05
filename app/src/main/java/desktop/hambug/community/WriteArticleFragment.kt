package desktop.hambug.community

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import desktop.hambug.R
import desktop.hambug.databinding.FragmentWriteBinding
import java.util.UUID

class WriteArticleFragment : Fragment(R.layout.fragment_write) {

    private lateinit var binding: FragmentWriteBinding

    private var selectedUri: Uri? = null

    // sdk 버전 분기 필요없이 사용가능한 사진선택도구
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // 이미지가 선택되면 url는 null이 아니다
            if (uri != null) {
                selectedUri = uri
                binding.photoImageView.setImageURI(uri)
                binding.plusImageView.isVisible = false
                binding.deleteImageView.isVisible = true

                view?.let { view -> Snackbar.make(view, "사진 선택 완료", Snackbar.LENGTH_SHORT).show() }
            } else {
                view?.let { view -> Snackbar.make(view, "사진 선택 실패", Snackbar.LENGTH_SHORT).show() }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWriteBinding.bind(view)

        binding.photoImageView.setOnClickListener {
            if (selectedUri == null) {
                startPicker()  // 사진선택 실행
            }
        }

        binding.deleteImageView.setOnClickListener {
            binding.photoImageView.setImageURI(null)
            selectedUri = null
            binding.deleteImageView.isVisible = false
            binding.plusImageView.isVisible = true
        }

        binding.submitButton.setOnClickListener {
            if (selectedUri != null) {
                // null 체크를 했지만
                // 전역이기 때문에 중간에 null이 들어갈 수 있기 때문에 예외처리
                val photoUri = selectedUri ?: return@setOnClickListener

                uploadImage(
                    photoUri = photoUri,
                    successHandler = {
                        // firestore 데이터 업로드
                        uploadArticle(it, binding.titleEditText.text.toString(), binding.descriptionEditText.text.toString())
                    },
                    errorHandler = {
                        Snackbar.make(view, "사진 업로드에 실패했습니다", Snackbar.LENGTH_SHORT).show()
                    })
            } else {
                Snackbar.make(view, "사진이 선택되지 않았습니다", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.backImageView.setOnClickListener {
            findNavController().navigate(WriteArticleFragmentDirections.actionBack())
        }
    }

    // PickVisualMedia 실행
    private fun startPicker() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun uploadImage(
        photoUri: Uri,
        successHandler: (String) -> Unit,
        errorHandler: (Throwable?) -> Unit
    ) {
        val fileName = "${UUID.randomUUID()}.png"
        Firebase.storage.reference.child("articles/photo").child(fileName)
            // 위의 경로에 파일을 쓴다
            .putFile(photoUri)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Firebase.storage.reference.child("articles/photo/$fileName")
                        .downloadUrl  // downloadUrl 생성 (http~ 형식의 주소)
                        .addOnSuccessListener {
                            successHandler(it.toString())
                        }.addOnFailureListener {
                            errorHandler(it)
                        }
                } else {
                    errorHandler(task.exception)
                }
            }
    }

    private fun uploadArticle(photoUri: String, title: String, description: String) {
        val articleId = UUID.randomUUID().toString()
        val articleModel = ArticleModel(
            articleId = articleId,
            createdAt = System.currentTimeMillis(),
            title = title,
            description = description,
            imageUri = photoUri
        )

        Firebase.firestore.collection("articles").document(articleId)
            .set(articleModel)
            .addOnSuccessListener {
                findNavController().navigate(WriteArticleFragmentDirections.actionWriteArticleFragmentToCommunityFragment())
                view?.let { view ->
                    Snackbar.make(view, "게시글 작성 완료", Snackbar.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                it.printStackTrace()
                view?.let { view ->
                    Snackbar.make(view, "게시글 작성 실패", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

}