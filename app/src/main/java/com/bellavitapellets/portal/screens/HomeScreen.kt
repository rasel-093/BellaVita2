package com.bellavitapellets.portal.screens

import androidx.compose.runtime.Composable
import com.bellavitapellets.portal.component.webViewLoader

@Composable
fun homeScreen(){
    webViewLoader(url = "https://portal.bellavitapellets.com/dosage/")
}