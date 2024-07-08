package kr.co.felici.remembering.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.felici.remembering.AppConstants
import kr.co.felici.remembering.api.MyApi
import kr.co.felici.remembering.databinding.FragmentAlbumphotoBinding
import kr.co.felici.rememberingtest.APIClient
import kr.co.felici.rememberingtest.domain.AlbumDTO
import kr.co.felici.rememberingtest.domain.AlbumDTOList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumPhotoFragment : Fragment() {

    private var _binding: FragmentAlbumphotoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var myApi: MyApi

    private lateinit var albumPhotoRecyclerView: RecyclerView
    private lateinit var albumPhotoAdapter: AlbumPhotoAdapter

    private var albumPhotoList = arrayListOf<AlbumDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppConstants.println("AlbumPhotoFragment -------------------- onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        AppConstants.println("AlbumPhotoFragment -------------------- onCreateView")

        val albumPhotoViewModel =
            ViewModelProvider(this).get(AlbumPhotoViewModel::class.java)

        _binding = FragmentAlbumphotoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        albumPhotoRecyclerView =
            binding.albumphotoFragmentRecyclerview
        AppConstants.println("리사이클러뷰: " + albumPhotoRecyclerView)

        albumPhotoRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // 서버에서 불러오기
        loadAlbumPosts()

//        albumPhotoViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppConstants.println("AlbumPhotoFragment -------------------- onViewCreated")

        albumPhotoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        albumPhotoAdapter = AlbumPhotoAdapter(albumPhotoList)
        albumPhotoRecyclerView.adapter = albumPhotoAdapter

        albumPhotoAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        AppConstants.println("AlbumPhotoFragment -------------------- onStart")

    }

    override fun onResume() {
        super.onResume()
        AppConstants.println("AlbumPhotoFragment -------------------- onResume")
        // 서버에서 불러오기
//        albumPhotoList.clear()
//        loadAlbumPosts()

        AppConstants.println("AlbumPhotoFragment - onResume - 인수로 넘길 albumPhotoList 갯수: ${albumPhotoList.size}")

//        AlbumPhotoAdapter(albumPhotoList)
//        val layoutManager = LinearLayoutManager(context)
//        albumPhotoRecyclerView.layoutManager = layoutManager
//
//        albumPhotoAdapter = context?.let { AlbumPhotoAdapter(it, albumPhotoList) }!!
//        albumPhotoRecyclerView.adapter = albumPhotoAdapter
//
//        albumPhotoAdapter.notifyDataSetChanged()

    }

    override fun onPause() {
        super.onPause()
        AppConstants.println("AlbumPhotoFragment -------------------- onPause")

    }

    override fun onStop() {
        super.onStop()
        AppConstants.println("AlbumPhotoFragment -------------------- onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        AppConstants.println(" ------------------  AlbumPhotoFragment - onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        AppConstants.println("AlbumPhotoFragment -------------------- onDestroy")

    }


    private fun setRecycler() {

        albumPhotoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        albumPhotoAdapter = AlbumPhotoAdapter(albumPhotoList)
        albumPhotoRecyclerView.adapter = albumPhotoAdapter


    }

    private fun loadAlbumPosts() {
        AppConstants.println(" ------------------  AlbumPhotoFragment - loadAlbumPosts()")
        myApi = APIClient.getClient()!!.create(MyApi::class.java)

        val call: Call<AlbumDTOList> = myApi.getAlbumPhotos()

        AppConstants.println("AlbumPhotoFragment - myApi.getAllPhotos()")

        call.enqueue(object : Callback<AlbumDTOList> {
            override fun onResponse(
                call: Call<AlbumDTOList>,
                response: Response<AlbumDTOList>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()

                    body?.photos?.forEach { album ->
                        AppConstants.println("제목: ${album.title}")
                        AppConstants.println("인포: ${album.info}")
                        AppConstants.println("사진: ${album.photo}")
                        AppConstants.println("--------------------------------------------------------------------")

                        albumPhotoList.add(album)

                    }

                }

                albumPhotoAdapter = AlbumPhotoAdapter(albumPhotoList)
                albumPhotoRecyclerView.adapter = albumPhotoAdapter
            }
            override fun onFailure(call: Call<AlbumDTOList>, t: Throwable) {
                AppConstants.println("onFailure() + ${t.message}")
            }
        })

    }

}