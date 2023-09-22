package com.example.homeworksavestatewithsharedpreferences

object ModelImpl:Model {
    private var numberOfClicks:Int = 0

    init {
        val savedNumber = MyApplication.getApp().getSavedData()
        if (savedNumber != 0) {
            numberOfClicks = savedNumber
        }
    }

    override fun getNumberOfClicks(): Int = numberOfClicks

    override fun addNumberOfClicks() {
        numberOfClicks += 1
    }
}