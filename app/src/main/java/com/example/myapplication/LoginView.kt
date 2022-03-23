package com.example.myapplication

import android.media.tv.TvContract
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//@Composable
//fun LoginPage(){
//    val user=
//        viewModel<UserPage>()
//
//    if (user.userid.value.isEmpty()){
//        LoginView(user)
//    } else{
//        Text(text = user.userid.value)
//    }
//}

//@Composable
//fun MainScaffoldView(){
//    Scaffold(
//        bottomBar = {},
//        topBar = {},
//        content = {}
//    )
//
//}


@Composable
fun LoginView(userVM: UserViewModel, navController: NavHostController) {
    var email by remember {
        mutableStateOf("") }

    var password by remember {
        mutableStateOf("") }

    var firstName by remember {
        mutableStateOf("") }

    var lastName by remember {
       mutableStateOf("") }

    var phoneNumber by remember {
        mutableStateOf("") }

    var route by remember { mutableStateOf("") }

    var isLoginOpen by remember { mutableStateOf(true) }

    val fAuth= Firebase.auth

    @Composable
    fun Logo(pic: Int) {
        Image(painter = painterResource(pic), contentDescription = "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row( // 1
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.background(Color(0xffed4956))
            ) {
                Logo(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row( // 1
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.background(Color(0xffed4956))
                ) {
                    Logo(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_focused)
                }
            }

            if (isLoginOpen) {
                Row( // 2
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Login",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffed4956)
                    )
                }

                Row( // 3
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Card(
                            shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                            border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
                            elevation = 10.dp
                        ) {
                            Column {
                                TextField(
                                    value = email,
                                    onValueChange = { email = it },
                                    placeholder = { Text(text = "Email") },
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true
                                )

                                TextField(
                                    value = password,
                                    onValueChange = { password = it },
                                    placeholder = { Text(text = "Password") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .size(52.dp)
                                .clickable {
                                    userVM.logInUser(email, password)
                                    navController.navigate(HOME_ROUTE)
                                },
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Row(
                                modifier = Modifier.background(Color(0xffed4956)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(com.google.firebase.appcheck.interop.R.drawable.common_google_signin_btn_icon_dark_normal),
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }}}

//    Column (
//  modifier = Modifier
//      .fillMaxWidth()
//      .height(200.dp),
//      verticalArrangement = Arrangement.SpaceEvenly,
//      horizontalAlignment = Alignment.CenterHorizontally,
//
//            ){
//      OutlinedTextField(value = email,
//          onValueChange = {email= it},
//          label = { Text(text = "Email")}
//      )
//
//        OutlinedTextField(value = password,
//          onValueChange = {password= it},
//          label = { Text(text = "Password")},
//        visualTransformation = PasswordVisualTransformation()
//        )
//
//  OutlinedButton(onClick = {
//      fAuth
//          .signInWithEmailAndPassword(email, password)
//          .addOnSuccessListener{info = "You are logged with account ${it.user!!.email.toString()}"}
////          user.loginUser(email,password)
//  }) {
//      Text(text = "Yes yes")
//
//          }
//    }
