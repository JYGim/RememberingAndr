package kr.co.felici.rememberingtest.domain

data class BoardVideoDTO(

    private val id: Long,
    private val path: String,
    private val originalFilename: String,
    private val fileSize: Long,


    private val letter: LetterDTO,
    private val memorialPost: MemorialPostDTOList

)
