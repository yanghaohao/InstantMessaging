package com.example.commen.extentions

import android.graphics.Color
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.showBadge(
    itemId: Int,
    count: Int,
) {
    getOrCreateBadge(itemId).apply {
        backgroundColor = Color.RED
        maxCharacterCount = 3
        number = count
        isVisible = count > 0
    }
}
