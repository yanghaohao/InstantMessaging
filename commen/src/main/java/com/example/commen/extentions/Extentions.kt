package com.example.commen.extentions

import android.content.Context
import android.graphics.Color
import com.google.android.material.bottomnavigation.BottomNavigationView

fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))

fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]{3,20}$"))

fun <K, V> MutableMap<K, V>.toVarargArray(): Array<Pair<K, V>> =
    map {
        Pair(it.key, it.value)
    }.toTypedArray()

/**
 * 将px值转换为dip或dp值，保证尺寸大小不变
 * @param context 上下文
 * @return dp的值
 */
fun Int.dp(context: Context): Int {
    val scale = context.resources.displayMetrics.density
    return (this / scale + 0.5f).toInt()
}

/**
 * 将dip或dp值转换为px值，保证尺寸不变
 * @param context 上下文
 * @param dipValue dp
 * @return px
 */
fun Int.px(context: Context): Int {
    val scale = context.resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

/**
 * 将px值转换为sp值，保证文字大小不变
 * @param context
 * @param pxValue
 * @return
 */
fun Int.sp(context: Context): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (this / fontScale + 0.5f).toInt()
}


