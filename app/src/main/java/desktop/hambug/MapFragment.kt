package desktop.hambug

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import desktop.hambug.databinding.FragmentMapBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var naverMap: NaverMap
    private var isMapInit = false

    // 아이템 눌리면
    private var restaurantListAdapter = RestaurantListAdapter {
        collapseBottomSheet()  // BottomSheet 내리기
        moveCamera(it, 16.0)  // 카메라 움직임
    }

    // 이전 마커표시 해제하기 위해 전역으로 관리
    private var markers = emptyList<Marker>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)

        binding.mapView.onCreate(savedInstanceState)

        binding.mapView.getMapAsync(this)

        binding.bottomSheetLayout.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantListAdapter
        }

        // 검색버튼을 눌렀을 때 사용할 수 있는 setOnQueryTextListener
        binding.searchView.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                // 빈 검색어 제외하는 예외처리
                if (query?.isNotEmpty() == true) {
                    SearchRepository.getBurgerRestaurant(query).enqueue(object : Callback<SearchResult> {
                        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                            // Log.e("aa", "${response.body().toString()}")
                            // 받아온 결과값을 마커에 찍어주기
                            val searchItemList = response.body()?.items.orEmpty()

                            if (searchItemList.isEmpty()) {
                                Toast.makeText(context, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
                                return
                            } else if (isMapInit.not()) {
                                Toast.makeText(context, "오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                                return
                            }

                            // 이전 마커표시 해제
                            markers.forEach {
                                it.map = null
                            }

                            markers = searchItemList.map {
                                // Tm128(it.mapx.toDouble() / 10000000, it.mapy.toDouble() / 10000000).toLatLng()
                                val latLng = LatLng(it.mapy.toDouble() / 10000000, it.mapx.toDouble() / 10000000)

                                Marker(latLng).apply {
                                    captionText = it.title
                                    map = naverMap  // naverMap이 초기화가 끝났을 때 일어나야함
                                    // 마커 커스텀
                                }
                            }

                            // 검색결과 5개 표시
                            restaurantListAdapter.submitList(searchItemList)

                            // BottomSheet 내리기
                            collapseBottomSheet()

                            // 검색결과의 첫번째꺼 위경도값으로 이동
                            moveCamera(markers.first().position, 13.0)

                        }

                        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                            Log.e("aa", "실패")
                        }

                    })
                    // 검색 이루어진 이후
                    return false  // 키보드 내려감
                // query 없을 경우
                } else {
                    return true  // 키보드 내려가지 않음
                }

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }

    // 카메라 이동
    private fun moveCamera(position: LatLng, zoomLevel: Double) {
        // naverMap을 사용하므로 예외처리
        if (isMapInit.not()) return

        val cameraUpdate = CameraUpdate.scrollAndZoomTo(position, zoomLevel)
            .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }

    // BottomSheet 내리기
    private fun collapseBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout.root)
        bottomSheetBehavior.state = STATE_COLLAPSED
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    // 지도가 준비되면 아래 호출됨
    override fun onMapReady(mapObject: NaverMap) {
        // 지도 객체 얻어오기
        naverMap = mapObject

        isMapInit = true

//        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.3666102, 126.9783881))
//            .animate(CameraAnimation.Easing)
//        naverMap.moveCamera(cameraUpdate)

        // 네이버지도의 API 사용 가능 (지도 객체 얻은 후여서)

    }
}