package kr.co.felici.rememberingtest.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// dummy video url로 동영상 테스트를 하기 위해 만든 삭제 예정인 viewmodel

class TestVideoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is video Fragment"
    }
    val text: LiveData<String> = _text

}