package com.bellavitapellets.portal.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bellavitapellets.portal.screens.accountScreen
import com.bellavitapellets.portal.screens.dosageScreen
import com.bellavitapellets.portal.screens.homeScreen
import com.bellavitapellets.portal.screens.orderScreen


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "dosage",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                homeScreen()
            }

            // route : dosage
            composable("dosage") {
                dosageScreen()
            }

            // route : order
            composable("order") {
                orderScreen()
            }

            // route : account
            composable("account") {
                accountScreen()
            }
        })

}
