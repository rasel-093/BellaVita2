package com.bellavitapellets.portal.component

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.Surface
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bellavitapellets.portal.R
import kotlinx.coroutines.delay

@Composable
fun webViewLoader(url: String){

    //..........................................................................custom full screen dialog
    val loaderDialogScreen = remember { mutableStateOf(true) }
    val openFullDialogCustom = remember { mutableStateOf(false) }
    val refreshScreen = remember { mutableStateOf(false) }
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    var progressValue by remember {
        mutableStateOf(0)
    }


    if (loaderDialogScreen.value) {
        // Dialog function
        Dialog(
            onDismissRequest = {
                loaderDialogScreen.value = false
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false // experimental
            )
        ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {



//                    Image(
//                        painter = painterResource(id = R.drawable.logo),
//                        contentDescription = null,
//                        contentScale = ContentScale.Fit,
//                        modifier = Modifier
//                            .height(200.dp)
//                            .fillMaxWidth(),
//
//                        )

                    //Spacer(modifier = Modifier.height(20.dp))
                    //.........................Text: title

//                    Text(
//                        text = "Loading...",
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .padding(top = 20.dp)
//                            .fillMaxWidth(),
//                        letterSpacing = 2.sp,
//                        fontWeight = FontWeight.Bold,
//                        style = MaterialTheme.typography.titleLarge,
//                        color = MaterialTheme.colorScheme.primary,
//                    )
                    progressBar(value = progressValue)
                    Spacer(modifier = Modifier.height(8.dp))

                    //.........................Text : description
                    Text(
                        text = "Please wait",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        letterSpacing = 3.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                }
        }

    }

   if(openFullDialogCustom.value){

        Dialog(
            onDismissRequest = {
                openFullDialogCustom.value = false
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false // experimental
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                Image(
                    painter = painterResource(id = R.drawable.wifi),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),

                    )

                Spacer(modifier = Modifier.height(20.dp))
                //.........................Text: title
                Text(
                    text = "Whoops!!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))

                //.........................Text : description
                Text(
                    text = "No Internet connection was found. Check your connection or try again.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    letterSpacing = 1.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                //.........................Spacer
                Spacer(modifier = Modifier.height(24.dp))

                val cornerRadius = 16.dp
                val gradientColor = listOf(Color(0xFFff669f), Color(0xFFff8961))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    onClick = {
                        refreshScreen.value = true
                    },

                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(cornerRadius)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(colors = gradientColor),
                                shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp)
                            )
                            .clip(RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp))
                            /*.background(
                                brush = Brush.linearGradient(colors = gradientColors),
                                shape = RoundedCornerShape(cornerRadius)
                            )*/
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Try Again",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }

            }
        }

    }

    if(refreshScreen.value){
        refreshScreen.value = false
        webViewLoader(url = url)
        openFullDialogCustom.value = false
        Log.v("MainActivity","Value of Refresh After: ${refreshScreen.value}")
        Log.v("MainActivity","Value of Error After: ${openFullDialogCustom.value}")
    }
    

    //..........................................................................
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true

                //webChromeClient
                webChromeClient = WebChromeClient()
                webChromeClient = object : WebChromeClient(){
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progressValue = newProgress
                    }
                }

                //webViewClient
                webViewClient = WebViewClient()
                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        // show dialog
                        if (view != null) {
                            backEnabled = view.canGoBack()
                        }
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        // hide dialog
                        loaderDialogScreen.value = false
                    }
                    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                        super.onReceivedError(view, request, error)
                        if(!isOnline(context)){
                            openFullDialogCustom.value = true
                        }
                    }

                }

                loadUrl(url)
                webView = this
            }
        }, update = {
            webView = it
        }
    )
    BackHandler(enabled = backEnabled){
        webView?.goBack()
    }
}


fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // For 29 api or above
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->    true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->   true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->   true
            else ->     false
        }
    }
    // For below 29 api
    else {
        @Suppress("DEPRECATION")
        if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting) {
            return true
        }
    }
    return false
}

@Composable
fun GradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape
) {

    
}

@Composable
fun reloadView(url: String){
    Log.v("MainActivity","Veiw Relaoding.......")
    webViewLoader(url = url)
}
