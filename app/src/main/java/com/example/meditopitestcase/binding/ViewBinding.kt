package com.example.meditopitestcase.binding


import android.text.format.DateFormat.format
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meditopitestcase.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("userImage")
fun setUserImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .timeout(15000)
        .apply(
            RequestOptions()
                .error(R.mipmap.ic_launcher)
                .fitCenter()
        )
        .fitCenter()
        .into(imageView)
}

@BindingAdapter("timetodate")
fun getDateTime(textView: TextView,s: Long) {
     try {
        val sdf = SimpleDateFormat("hh:mm dd/MM/yyyy")
        val netDate = Date(s * 1000)
        textView.text = sdf.format(netDate).toString()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
