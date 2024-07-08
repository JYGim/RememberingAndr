package kr.co.felici.remembering.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.felici.remembering.AppConstants
import kr.co.felici.remembering.api.MyApi
import kr.co.felici.remembering.api.TestVideoApi
import kr.co.felici.remembering.databinding.FragmentTestvideoBinding
import kr.co.felici.remembering.dto.TestVideoDto
import kr.co.felici.remembering.dto.TestVideoDtoList
import kr.co.felici.remembering.model.TestVideoModel
import kr.co.felici.rememberingtest.APIClient
import kr.co.felici.rememberingtest.ui.gallery.TestVideoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// dummy video url로 동영상 테스트를 하기 위해 만든 삭제 예정인 fragment

class TestVideoFragment : Fragment() {

    private var _binding: FragmentTestvideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var testVideoApi: TestVideoApi

    private lateinit var testVideoRecyclerView: RecyclerView
    private lateinit var testVideoAdapter: TestVideoAdapter

    private var testVideoList = arrayListOf<TestVideoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppConstants.println("TestVideoFragment -------------------- onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AppConstants.println("TestVideoFragment -------------------- onCreateView")

        val testVideoViewMode =
            ViewModelProvider(this).get(TestVideoViewModel::class.java)

        _binding = FragmentTestvideoBinding.inflate(inflater, container, false)

        val root: View = binding.root

        testVideoRecyclerView =
            binding.testvideoFragmentRecyclerview
        AppConstants.println("테스트비디오리사이클러뷰: " + testVideoRecyclerView)

        testVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // 서버에서 불러오기
//        getTestVideoList()
        getVideoList()

//        setRecycler()

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppConstants.println("TestVideoFragment -------------------- onViewCreated")

        testVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        testVideoAdapter = TestVideoAdapter(testVideoList)
        testVideoRecyclerView.adapter = testVideoAdapter

        testVideoAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        AppConstants.println("TestVideoFragment -------------------- onStart")

    }

    override fun onResume() {
        super.onResume()
        AppConstants.println("TestVideoFragment -------------------- onResume")
        // 서버에서 불러오기
//        albumVideoList.clear()
//        loadAlbumPosts()

        AppConstants.println("TestVideoFragment - onResume - 인수로 넘길 testVideoList 갯수: ${testVideoList.size}")

//        albumVideoAdapter(albumVideoList)
//        val layoutManager = LinearLayoutManager(context)
//        testVideoRecyclerView.layoutManager = layoutManager
//
//        albumVideoAdapter = context?.let { albumVideoAdapter(it, albumVideoList) }!!
//        testVideoRecyclerView.adapter = albumVideoAdapter
//
//        albumVideoAdapter.notifyDataSetChanged()

    }

    override fun onPause() {
        super.onPause()
        AppConstants.println("TestVideoFragment -------------------- onPause")

    }

    override fun onStop() {
        super.onStop()
        AppConstants.println("TestVideoFragment -------------------- onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        AppConstants.println(" ------------------  TestVideoFragment - onDestroyView")
//        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        AppConstants.println("TestVideoFragment -------------------- onDestroy")

    }


    private fun setRecycler() {

        testVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        albumVideoAdapter = AlbumVideoAdapter(albumVideoList)
        testVideoAdapter = TestVideoAdapter(testVideoList)
        testVideoRecyclerView.adapter = testVideoAdapter


    }

    private fun getTestVideoList() {

        AppConstants.println(" ------------------  TestVideoFragment - getTestVideoList()")
        testVideoApi = APIClient.getClient()!!.create(TestVideoApi::class.java)

        val call: Call<TestVideoDtoList> = testVideoApi.getTestVideos()

        call.enqueue(object : Callback<TestVideoDtoList> {
            override fun onResponse(call: Call<TestVideoDtoList>, response: Response<TestVideoDtoList>) {
                if (response.isSuccessful.not()) {
                    AppConstants.println(" ------------------  TestVideoFragment - onResponse() isSuccessful Fail!!!!")

                    return
                }

                val body = response.body()

                body!!.videos.forEach {
                    testVideoList.add(it)
                }
//                testVideoAdapter = TestVideoAdapter(testVideoList)
                testVideoRecyclerView.adapter = testVideoAdapter
            }

            override fun onFailure(call: Call<TestVideoDtoList>, t: Throwable) {
                AppConstants.println("onFailure() + ${t.message}")
            }
        })
    }

    private fun getVideoList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TestVideoApi::class.java).also {

            it.listVideos()
                .enqueue(object : Callback<TestVideoDto> {
                    override fun onResponse(call: Call<TestVideoDto>, response: Response<TestVideoDto>) {
                        if (response.isSuccessful.not()) {
                            return
                        }

                        response.body()?.let { videoDto ->
                            videoDto.videos.forEach {
                                testVideoList.add(it)
                            }
                        }

                        testVideoRecyclerView.adapter = testVideoAdapter

                    }

                    override fun onFailure(call: Call<TestVideoDto>, t: Throwable) {
                        // 예외처리
                    }
                })
        }
    }
}




