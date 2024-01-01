package desktop.hambug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import desktop.hambug.databinding.ActivityLoginBinding
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

//    val auth = Firebase.auth
//    var verificationId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        // var phoneNumber = binding.phoneNumberEditText.text.toString()

//        binding.sendButton.setOnClickListener {
//
//            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                // 번호인증이 끝난 상태로 따로 인증번호를 입력할 필요없는 상태
//                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                    Log.e("test", "인증 끝난상태")
//                }
//                // 번호 인증 실패 상태
//                override fun onVerificationFailed(e: FirebaseException) {
//                    Log.e("test", "번호인증 실패")
//                }
//                // 번호는 확인 되었으나 인증코드를 입력해야 하는 상태
//                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
//                    this@LoginActivity.verificationId = verificationId
//
//                    // Toast.makeText(this@MainActivity, "$verificationId", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this@LoginActivity, "인증번호 잘옴", Toast.LENGTH_SHORT).show()
//                    Log.e("test", "인증번호 잘옴")
//                }
//            }
//
//            val options = PhoneAuthOptions.newBuilder(auth)
//                .setPhoneNumber("+821011113333") // Phone number to verify
//                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                .setActivity(this) // Activity (for callback binding)
//                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
//                .build()
//            PhoneAuthProvider.verifyPhoneNumber(options)
//            auth.setLanguageCode("kr")
//
//            Log.e("test", "id : $verificationId")
//        }

//        binding.confirmButton.setOnClickListener {
//            Log.e("test", "id : $verificationId")
//
//            val credential = PhoneAuthProvider.getCredential(verificationId, "123455")
//            signInWithPhoneAuthCredential(credential)
//        }

    }

//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    //인증성공
//                    Log.e("test", "성공")
//                }
//                else {
//                    //인증실패
//                    Log.e("test", "실패")
//                }
//            }
//    }

}