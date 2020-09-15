package com.example.studyroom

import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    val dataList = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setEditTextListener()
        handleRequests()
    }

    fun handleRequests(filter: String = "all") {
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=perl&site=stackoverflow"
//        val image_url = "https://www.gravatar.com/avatar/c59430d83c59066e849366eed94e4ff7?s=128&d=identicon&r=PG&f=1"


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONObject(response)

                val listdata: MutableList<Items> = ArrayList()
                val jArray = jsonObject.getJSONArray("items")
                if (jArray != null) {
                    for (i in 0 until jArray.length()) {
                        val tags = jArray.getJSONObject(i).getString("tags")
                        val imageUrl = jArray.getJSONObject(i).getJSONObject("owner").getString("profile_image")
                        val title = jArray.getJSONObject(i).getString("title")
//                        val imageView = ImageView(this)
//                        var img: ImageView = ImageView(this)
//                        queue.add(getImageRequest(imageUrl, img))

//                      listdata.add(Items(tags, image_url, title, pic))
                        if(filter == "all")
                            dataList.add(Data(title, tags))
                        else if(tags.contains(filter))
                            dataList.add(Data(title, tags))
                    }

                    val adapter = RecyclerViewAdapter(this, dataList)
                    recyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = adapter
                }
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        queue.add(stringRequest)
    }

    fun getImageRequest(imageUrl: String, imageView: ImageView): ImageRequest {
        return ImageRequest(imageUrl,
            Response.Listener { bitmap ->  ImageView(this).setImageBitmap(bitmap) }, 0, 0, null,
            Response.ErrorListener { textView.text = "That didn't work!" })
    }

    fun setEditTextListener() {
        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        Toast.makeText(applicationContext,"executed before making any change over EditText",Toast.LENGTH_SHORT).show()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                        Toast.makeText(applicationContext,"executed while making any change over EditText", Toast.LENGTH_SHORT).show()
            }
            override fun afterTextChanged(p0: Editable?) {
                        Toast.makeText(applicationContext,"EditText ${editText.text.toString()}",
                            Toast.LENGTH_SHORT).show()
                handleRequests(editText.text.toString())
            }
        })
    }
}