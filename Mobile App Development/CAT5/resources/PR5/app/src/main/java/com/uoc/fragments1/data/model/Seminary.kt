package com.uoc.fragments1.data.model

import android.view.View
import androidx.annotation.DrawableRes

data class Seminary(val id: Int, val name: String, val duration: Int,val level:String,val image_path: String?,
                    @DrawableRes
                      val image: Int? = null,
                    var view: View? = null)
