package com.example.myapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


const val HOME_ROUTE = "home"
const val LOGINSIGNUP_ROUTE = "logInSignUp"
const val PROFILE_ROUTE = "profile"



const val ADMIN_ROUTE = "ADMIN"
const val USER_ROUTE = "USER"


val fAuth = Firebase.auth
val user = fAuth.currentUser

@Composable
fun Main() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBarView(navController) },
        bottomBar = { BottomBarView(navController) },
        content = { MainContentView(navController) }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(route = HOME_ROUTE) { HomeView() }
        composable(route = LOGINSIGNUP_ROUTE) { LoginView(UserViewModel(), navController) }
        composable(route = PROFILE_ROUTE) { UserView(navController, UserViewModel()) }
    }
}

@Composable
fun TopBarView(navController: NavHostController) {

    var isLoggedIn by remember { mutableStateOf(false) }
    if (user != null) {
        isLoggedIn = !isLoggedIn
    }

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5722))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource( R.drawable.ic_icone) ,
            contentDescription = "",
            modifier = Modifier.clickable { navController.navigate(LOGINSIGNUP_ROUTE) }
        )
    }
}

@Composable
fun BottomBarView(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5722))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_icone),
            contentDescription = "home",
            modifier = Modifier.clickable { navController.navigate(HOME_ROUTE) }
        )
    }
}
