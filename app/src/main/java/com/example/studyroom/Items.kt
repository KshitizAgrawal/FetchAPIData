package com.example.studyroom

import android.graphics.Bitmap

class Items(arr: String, img: String, title: String) {
    private var tags = arr
    private var imageUrl = img
    private var title = title

    fun setTags(str: String) {
        tags = str
    }

    fun getTags() : String{
        return tags
    }

    fun setImageUrl(str: String) {
        imageUrl = str
    }

    fun getImageUrl() :String{
        return imageUrl
    }

    fun setTitle(str: String) {
        title = str
    }

    fun getTitle() : String{
        return title
    }

//    fun setPic(bmp: Bitmap) {
//        pic = bmp
//    }
//
//    fun getPic(): Bitmap {
//        return pic
//    }
}