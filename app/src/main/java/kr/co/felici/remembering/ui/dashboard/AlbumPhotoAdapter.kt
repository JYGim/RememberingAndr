package kr.co.felici.remembering.ui.dashboard

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.felici.remembering.AppConstants
import kr.co.felici.remembering.R
import kr.co.felici.remembering.databinding.AlbumPhotoDetailBinding
import kr.co.felici.rememberingtest.domain.AlbumDTO


class AlbumPhotoAdapter(
    private val albumPhotoList: List<AlbumDTO>
) : RecyclerView.Adapter<AlbumPhotoAdapter.ViewHolder>() {

    private var binding: AlbumPhotoDetailBinding? = null

    private val baseUrl: String = "http://remembering.website/"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumPhotoAdapter.ViewHolder {
        AppConstants.println("AlbumPhotoAdapter - onCreateViewHolder")
        AppConstants.println("onCreateViewHolder 인수로 받은 albumPhotoList 갯수 - ${albumPhotoList.size}")
        binding =
            AlbumPhotoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: AlbumPhotoAdapter.ViewHolder, position: Int) {
        AppConstants.println("AlbumPhotoAdapter - onBindViewHolder")

        val albumPhoto = albumPhotoList[position]
        val photo = albumPhotoList[position].photo
        val picUri = Uri.parse(baseUrl + photo)

        AppConstants.println("picUri: ${picUri}")

        if(photo!!.isNotBlank()) {
            Glide.with(holder.photoImageView.context)
                .load(picUri)
                .error(R.drawable.ic_baseline_calendar_today_24)
                .into(holder.photoImageView)
        }

//        holder.titleTextView.text = albumPhoto.title
        holder.infoTextView.text = albumPhoto.info
        holder.dateTextView.text = albumPhoto.estimatedYear

    }

    override fun getItemCount(): Int {
        AppConstants.println("AlbumPhotoAdapter getItemCount 갯수 = ${albumPhotoList.size}")
        return albumPhotoList.size
    }


    inner class ViewHolder(binding: AlbumPhotoDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        var photocontainer: LinearLayout = binding!!.photoContainer
//        var progressBar = binding!!.progressBar
        var photoImageView = binding.albumPhotoImageView

//        val titleTextView = binding.titleTextView
        val infoTextView = binding.infoTextView
        val dateTextView = binding.dateTextView

    }

}