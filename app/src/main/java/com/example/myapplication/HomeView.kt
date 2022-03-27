package com.example.myapplication

import android.media.tv.TvContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun HomeView(navController: NavHostController) {
        val fireStore = Firebase.firestore
        val fAuth = Firebase.auth
        var currentUserRoute by remember { mutableStateOf("") }
    val color = Color(0xffed4956)


        fireStore
            .collection("users")
            .document(fAuth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                currentUserRoute = it.get("route").toString()
            }
    Column(
        modifier = Modifier.padding(10.dp, 60.dp),
       verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text( "Don't know what dessert to choose for today? We can help you! " +
                "Click the button to sign in and let's get started!",
//            color= Color(0xffed4956),
//            fontSize = 30. dp,
        modifier = Modifier.padding(4.dp)
        )
Button(
    modifier = Modifier
        .padding(5.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = color),
    onClick = {
    navController.navigate(LOGINSIGNUP_ROUTE)
},){

    Icon(
        painter = painterResource(R.drawable.ic_login),
        contentDescription = "",
        modifier = Modifier.clickable { navController.navigate(LOGINSIGNUP_ROUTE) }
    )
}
        Box(
            modifier = Modifier
                .background(Color(0xffed4956))
                .padding(2.dp, 2.dp)
        ) {
            Logo1(R.drawable.ic_dessert)

        }
    }
    }
@Composable
fun Logo1(jpg: Int) {
    Image(painter = painterResource(jpg), contentDescription ="" )

}
//    when (currentUserRoute) {
//        ADMIN_ROUTE -> Text(text = "You are $currentUserRoute")
//        USER_ROUTE -> Text(text = "You are $currentUserRoute")






