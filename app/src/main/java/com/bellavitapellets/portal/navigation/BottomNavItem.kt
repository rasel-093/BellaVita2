package com.bellavitapellets.portal.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val route:String
)
