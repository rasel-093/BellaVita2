package com.bellavitapellets.portal.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart

object BottomNavItems {
    val bottomNavItem = listOf<BottomNavItem>(
        BottomNavItem(
            label = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Dosage",
            unselectedIcon = Icons.Outlined.Create,
            selectedIcon = Icons.Filled.Create,
            route = "dosage"
        ),
        BottomNavItem(
            label = "Order",
            unselectedIcon = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart,
            route = "order"
        ),
        BottomNavItem(
            label = "Account",
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            route = "account"
        )
    )
}