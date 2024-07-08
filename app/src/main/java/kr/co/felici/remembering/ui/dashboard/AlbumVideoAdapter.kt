package kr.co.felici.remembering.ui.dashboard

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import kr.co.felici.remembering.AppConstants
import kr.co.felici.remembering.databinding.AlbumVideoDetailBinding
import kr.co.felici.remembering.dto.TestVideoDto
import kr.co.felici.remembering.model.TestVideoModel
import kr.co.felici.rememberingtest.domain.AlbumDTO


class AlbumVideoAdapter(
    private val albumVideoList: List<AlbumDTO>
) : RecyclerView.Adapter<AlbumVideoAdapter.ViewHolder>() {

    private var binding: AlbumVideoDetailBinding? = null

    private lateinit var videoUri: Uri

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumVideoAdapter.ViewHolder {

        binding = AlbumVideoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: AlbumVideoAdapter.ViewHolder, position: Int) {
        AppConstants.println("AlbumVideoAdapter - onBindViewHolder")

        val video = albumVideoList[position].photo

        AppConstants.println("video: ${video}")

        videoUri = Uri.parse("http://remembering.website/" + video)

        AppConstants.println("videoUri: ${videoUri}")

        val player = ExoPlayer.Builder(this.binding!!.root.context).build()

        binding!!.albumVideoView.player = player

        val mediaItem = MediaItem.fromUri(videoUri)

        player.setMediaItem(mediaItem)

        player.prepare()
        player.play()

    }

    override fun getItemCount(): Int {
        return albumVideoList.size
    }

    inner class ViewHolder(binding: AlbumVideoDetailBinding) :
            RecyclerView.ViewHolder(binding.root) {


    }







}