package com.example.message.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.commen.view.ActionBar
import com.example.message.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@BindingAdapter(value = ["timeStamp", "isMsgItem"])
fun timeFormat(
    text: TextView,
    time: Long,
    isMsgItem: Boolean,
) {
    val midnightToday = getMidnightTime()
    val dayDiff = ((time - midnightToday) / (1000 * 3600 * 24)).toInt()

    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    val hourAndMinute = sdf.format(time).split(" ")[1]
    text.text =
        when (dayDiff) {
            0 -> {
                hourAndMinute
            }

            1 -> {
                "昨天 ${if (isMsgItem) "" else hourAndMinute}"
            }

            2 -> {
                "前天 ${if (isMsgItem) "" else hourAndMinute}"
            }

            else -> {
                sdf.format(time)
            }
        }
}

fun getMidnightTime(): Long {
    val calendar =
        Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
    return calendar.timeInMillis
}

@BindingAdapter("glideImg")
fun glideImg(
    imageView: ImageView,
    src: String,
) {
    Glide.with(imageView.context).load(src).into(imageView)
}

@BindingAdapter("sendSuccess")
fun showLoadMsg(
    imageView: ImageView,
    sendSuccess: Int,
) {
    when (sendSuccess) {
        -1 -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.mipmap.msg_error)
        }

        1 -> imageView.visibility = View.GONE
        else -> imageView.visibility = View.VISIBLE
    }
}

@BindingAdapter("actionBarName")
fun actionBarName(text: ActionBar,name:String){
    text.setTitle(name)
}
