package kr.co.felici.rememberingtest.domain

data class LetterDTO(
    private val id: Long,
    private val contents:String,
    private val images: List<BoardImageDTO>,
    private val videos: List<BoardVideoDTO>,
//    private User User
)
