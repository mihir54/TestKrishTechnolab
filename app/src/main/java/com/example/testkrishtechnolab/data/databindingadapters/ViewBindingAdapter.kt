package com.example.testkrishtechnolab.data.databindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.testkrishtechnolab.R
import java.text.NumberFormat
import java.util.*

@BindingAdapter(value = ["imageUrl", "placeholderImage", "errorImage"], requireAll = false)
fun loadImageFromInternet(view: ImageView, imageUrl: String, placeholderImage: Boolean, errorImage: Boolean) {

    val requestOptions : RequestOptions = RequestOptions()

    if (placeholderImage) requestOptions.placeholder(R.drawable.ic_placeholder)
    if (errorImage) requestOptions.error(R.drawable.ic_error_image)

    Glide.with(view.context)
        .setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .override(250, 250)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(view)
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
        //this.layoutManager = LinearLayoutManager(context)

    }
}

@BindingAdapter("priceText")
fun setPriceText(view: TextView, price: Double) {
    view.text = "$ $price"
}


@BindingAdapter("ratingText")
fun setTotalRatingText(view: RatingBar, rating: Int) {
    view.rating = rating.toFloat()
}



@BindingAdapter(value = ["setupVisibility"])
fun progressVisibility(view: ProgressBar, loggingIn : Boolean) {
    if (loggingIn) view.visibility = View.VISIBLE
    else  view.visibility = View.GONE
}
