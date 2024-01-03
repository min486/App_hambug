package desktop.hambug.burger

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import desktop.hambug.R
import desktop.hambug.databinding.FragmentFranchiseBinding
import java.io.IOException

class FranchiseFragment: Fragment(R.layout.fragment_franchise) {
    private lateinit var binding: FragmentFranchiseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFranchiseBinding.bind(view)

        val data = getJsonData("franchise_list.json")

        binding.franchiseRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = FranchiseAdapter(data!!)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    // json 파일 읽어와서 Franchise로 만들어주는 함수
    private fun getJsonData(filename: String): Franchise? {
        val assetManager = resources.assets
        var result: Franchise? = null
        try {
            val inputStream = assetManager.open(filename)  // 전달받은 파일이름으로 파일 열기
            val reader = inputStream.bufferedReader()  // 내용 text로 변환
            val gson = Gson()
            result = gson.fromJson(reader, Franchise::class.java)  // 읽어온 text데이터를 Franchise 클래스로 변환
        } catch (e:IOException) {
            e.printStackTrace()
        }
        return result
    }

}