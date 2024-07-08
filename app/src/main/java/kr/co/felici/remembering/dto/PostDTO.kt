package kr.co.felici.rememberingtest.dto

import kr.co.felici.rememberingtest.domain.BoardImageDTO
import kr.co.felici.rememberingtest.domain.BoardVideoDTO
import java.time.LocalDateTime

data class PostDTO(
    private val id: Long,
    private val writer: String,
    private val contents: String,
    private val pw: String,
    private val postedAt: LocalDateTime,
    private val modifiedAt: LocalDateTime,
    private val images: List<BoardImageDTO>,
    private val videos: List<BoardVideoDTO>
)
