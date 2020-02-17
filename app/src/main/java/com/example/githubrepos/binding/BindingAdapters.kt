package com.example.githubrepos.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageFromUrl")
fun bindImage(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrBlank()) {
        /*val requestOption = RequestOptions()
            .placeholder(android.R.drawable.ic_menu_add).centerCrop()*/

        Glide.with(view.context)
            .load(imageUrl)
            /*.transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOption)*/
            .into(view)
    }

}