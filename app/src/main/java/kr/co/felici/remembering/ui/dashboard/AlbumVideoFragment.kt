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
import kr.co.felici.remembering.databinding.FragmentAlbumvideoBinding
import kr.co.felici.remembering.dto.TestVideoDto
import kr.co.felici.remembering.model.TestVideoModel
import kr.co.felici.rememberingtest.APIClient
import kr.co.felici.rememberingtest.domain.AlbumDTO
import kr.co.felici.rememberingtest.domain.AlbumVideoDTOList
import kr.co.felici.rememberingtest.ui.gallery.AlbumVideoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumVideoFragment : Fragment() {

    private var _binding: FragmentAlbumvideoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var myApi: MyApi

    private lateinit var albumVideoRecyclerView: RecyclerView
    private lateinit var albumVideoAdapter: AlbumVideoAdapter

    private var albumVideoList = arrayListOf<AlbumDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppConstants.println("VideoFragment -------------------- onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AppConstants.println("VideoFragment -------------------- onCreateView")

        val videoViewModel =
            ViewModelProvider(this).get(AlbumVideoViewModel::class.java)

        _binding = FragmentAlbumvideoBinding.inflate(inflater, container, false)

        val root: View = binding.root

        albumVideoRecyclerView =
            binding.videoFragmentRecyclerview
        AppConstants.println("앨범비디오 리사이클러뷰: " + albumVideoRecyclerView)

        albumVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // 서버에서 불러오기
        loadAlbumVideos()


//        setRecycler()



//        albumVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        albumVideoAdapter = albumVideoAdapter(albumVideoList)
//        albumVideoRecyclerView.adapter = albumVideoAdapter
//
//        albumVideoAdapter.notifyDataSetChanged()

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppConstants.println("VideoFragment -------------------- onViewCreated")

        albumVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        albumVideoAdapter = AlbumVideoAdapter(albumVideoList)
        albumVideoRecyclerView.adapter = albumVideoAdapter

        albumVideoAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        AppConstants.println("VideoFragment -------------------- onStart")

    }

    override fun onResume() {
        super.onResume()
        AppConstants.println("VideoFragment -------------------- onResume")
        // 서버에서 불러오기
//        albumVideoList.clear()
//        loadAlbumPosts()

        AppConstants.println("VideoFragment - onResume - 인수로 넘길 albumVideoList 갯수: ${albumVideoList.size}")

//        albumVideoAdapter(albumVideoList)
//        val layoutManager = LinearLayoutManager(context)
//        albumVideoRecyclerView.layoutManager = layoutManager
//
//        albumVideoAdapter = context?.let { albumVideoAdapter(it, albumVideoList) }!!
//        albumVideoRecyclerView.adapter = albumVideoAdapter
//
//        albumVideoAdapter.notifyDataSetChanged()
        
    }

    override fun onPause() {
        super.onPause()
        AppConstants.println("VideoFragment -------------------- onPause")

    }

    override fun onStop() {
        super.onStop()
        AppConstants.println("VideoFragment -------------------- onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        AppConstants.println(" ------------------  VideoFragment - onDestroyView")
//        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        AppConstants.println("VideoFragment -------------------- onDestroy")

    }


    private fun setRecycler() {

        albumVideoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        albumVideoAdapter = AlbumVideoAdapter(albumVideoList)
        albumVideoRecyclerView.adapter = albumVideoAdapter


    }

    private fun loadAlbumVideos() {
        AppConstants.println(" ------------------  VideoFragment - loadAlbumPosts()")
        myApi = APIClient.getClient()!!.create(MyApi::class.java)

        val call: Call<AlbumVideoDTOList> = myApi.getAlbumVideos()

        AppConstants.println("VideoFragment - myApi.getAlbumVideos()")

        call.enqueue(object : Callback<AlbumVideoDTOList> {
            override fun onResponse(
                call: Call<AlbumVideoDTOList>,
                response: Response<AlbumVideoDTOList>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()

                    body?.videos?.forEach { album ->
                        AppConstants.println("제목: ${album.title}")
                        AppConstants.println("인포: ${album.info}")
//                        AppConstants.println("사진: ${album.video}")
                        AppConstants.println("사진: ${album.photo}")
                        AppConstants.println("--------------------------------------------------------------------")

                        albumVideoList.add(album)

                    }

                } else {
                    AppConstants.println("response 실패")
                }

//                albumVideoAdapter = AlbumVideoAdapter(albumVideoList)
                albumVideoRecyclerView.adapter = albumVideoAdapter
            }
            override fun onFailure(call: Call<AlbumVideoDTOList>, t: Throwable) {
                AppConstants.println("onFailure() + ${t.message}")
            }
        })

    }

}


