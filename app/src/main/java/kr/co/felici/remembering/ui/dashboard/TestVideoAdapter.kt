package kr.co.felici.remembering.ui.dashboard

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import kr.co.felici.remembering.AppConstants
import kr.co.felici.remembering.R
import kr.co.felici.remembering.databinding.TestVideoDetailBinding
import kr.co.felici.remembering.model.TestVideoModel

// dummy video url로 동영상 테스트를 하기 위해 만든 삭제 예정인 adapter

class TestVideoAdapter(
    private val testVideoList: List<TestVideoModel>
) : RecyclerView.Adapter<TestVideoAdapter.ViewHolder>() {

    private var binding: TestVideoDetailBinding? = null

    private lateinit var videoUri: Uri
    private lateinit var bottomPlayerControlButton: ImageView
    private lateinit var testVideoTitleTextView: TextView
    private lateinit var testVideoDescTextView: TextView

    private var player: ExoPlayer? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestVideoAdapter.ViewHolder {

        binding = TestVideoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        testVideoTitleTextView = binding!!.testTitleTextView
        testVideoDescTextView = binding!!.testDescTextView
        bottomPlayerControlButton = binding!!.bottomPlayerControlButton

        initPlayer(binding!!)
        initControlButton(binding!! )

        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: TestVideoAdapter.ViewHolder, position: Int) {
        AppConstants.println("TestVideoAdapter - onBindViewHolder")

        val testVideoModel = testVideoList[position]
        val testVideo = testVideoModel.sources

        AppConstants.println("testVideo: ${testVideo}")

        videoUri = Uri.parse(testVideo)

        AppConstants.println("videoUri: ${videoUri}")

        player = ExoPlayer.Builder(binding!!.root.context).build()

        binding!!.testVideoView.player = player

        val mediaItem = MediaItem.fromUri(videoUri)

        player!!.setMediaItem(mediaItem)

        player!!.prepare()
        player!!.play()

        testVideoTitleTextView.text = testVideoModel.title
        testVideoDescTextView.text = testVideoModel.subtitle

        AppConstants.println("title : ${testVideoModel.title}")
        AppConstants.println("description : ${testVideoModel.description}")

    }

    override fun getItemCount(): Int {
        return testVideoList.size
    }

    inner class ViewHolder(binding: TestVideoDetailBinding) :
            RecyclerView.ViewHolder(binding.root) {




    }


    private fun initPlayer(binding: TestVideoDetailBinding) {

        binding.root.context?.let {
            player = ExoPlayer.Builder(binding.root.context).build()
        }

        binding.testVideoView.player = player

        binding.let {
            player?.addListener(
                object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)

                        if (isPlaying) {
                            it.bottomPlayerControlButton.setImageResource(R.drawable.ic_baseline_pause_24)

                        } else {
                            it.bottomPlayerControlButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)

                            // Not playing because playback is paused, ended, suppressed, or the player
                            // is buffering, stopped or failed. Check player.playWhenReady,
                            // player.playbackState, player.playbackSuppressionReason and
                            // player.playerError for details.
                        }
                    }
                }
            )
        }
    }

    private fun initControlButton(binding: TestVideoDetailBinding) {
        bottomPlayerControlButton.setOnClickListener {
            val player = this.player ?: return@setOnClickListener

            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }

    }







}