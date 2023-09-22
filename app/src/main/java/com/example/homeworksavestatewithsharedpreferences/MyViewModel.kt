package com.example.homeworksavestatewithsharedpreferences

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val model:Model = ModelImpl
    val uiStateLiveData = MutableLiveData<UIState>(UIState.NoClickedValue)
    val intentLiveData = MutableLiveData<Intent>()
    private val observer  = Observer<Intent> {
        addItem()
    }

    init {
        intentLiveData.observeForever(observer)
        if ( model.getNumberOfClicks() != 0) {
            uiStateLiveData.value = UIState.ClickedValue(model.getNumberOfClicks())
        }
    }

    private fun addItem() {
        model.addNumberOfClicks()
        uiStateLiveData.value = UIState.ClickedValue(model.getNumberOfClicks())
    }

    override fun onCleared() {
        super.onCleared()

        intentLiveData.removeObserver(observer)
        MyApplication .getApp().saveData(model.getNumberOfClicks())
    }
}

sealed class UIState {
    object NoClickedValue:UIState()
    class ClickedValue(val numberOfClicks:Int):UIState()
}

sealed class Intent {
    object AddItem: Intent()
}