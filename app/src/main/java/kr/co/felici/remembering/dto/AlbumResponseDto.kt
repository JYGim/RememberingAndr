package kr.co.felici.rememberingtest.dto

import java.time.LocalDate


data class AlbumResponseDto(

    val id: Long? = null,

    val title: String? = null,

    val info: String? = null,

    val descriptions: String? = null,

    val photo: String? = null,

    val video: String? = null,

    val isWhenPictureWasTakenclear: String? = null,

    val estimatedYear: String? = null,

    val tookIn: LocalDate? = null,

    val displayOrder: Int = 0
)