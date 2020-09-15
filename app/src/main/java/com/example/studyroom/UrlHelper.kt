package com.example.studyroom

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject


class UrlHelper {
    companion object {
        fun getStringRequest(url: String, listData: MutableList<Items>): StringRequest {

            return StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    val jsonObject = JSONObject(response)
                    val jArray = jsonObject.getJSONArray("items")
                    if (jArray != null) {
                        for (i in 0 until jArray.length()) {
                            val tags = jArray.getJSONObject(i).getString("tags")
                            val image_url = jArray.getJSONObject(i).getJSONObject("owner")
                                .getString("profile_image")
                            val title = jArray.getJSONObject(i).getString("title")
//                            val pic = getImageRequest(image_url, imageView)
//                            listdata.add(Items(tags, image_url, title, pic))
                        }
                    }
                },
                Response.ErrorListener {})
        }

        fun getImageRequest(url: String, imageView: ImageView): ImageRequest {

            return ImageRequest(url,
                Response.Listener { bitmap -> imageView.setImageBitmap(bitmap) }, 0, 0, null,
                Response.ErrorListener {})

        }
    }

}