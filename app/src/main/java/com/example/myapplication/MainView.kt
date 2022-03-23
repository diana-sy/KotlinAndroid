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


val fAuth = Firebase.auth
val user = fAuth.currentUser

@Composable
fun Main() {
    val navController = rememberNavController()
    val scState = rememberScaffoldState( rememberDrawerState(DrawerValue.Closed) )

    Scaffold(
        scaffoldState = scState,
        topBar = { TopBarView(navController, scState) },
        bottomBar = { BottomBarView(navController) },
        content = { MainContentView(navController) },
        drawerContent = { DrawerLayoutView(navController, scState) }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LOGINSIGNUP_ROUTE) {
        composable(route = HOME_ROUTE) { HomeView() }
        composable(route = LOGINSIGNUP_ROUTE) { LoginView(UserViewModel(), navController) }
        composable(route = PROFILE_ROUTE) { UserPage() }
    }
}

@Composable
fun TopBarView(navController: NavHostController, scState: ScaffoldState) {

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
            painter = painterResource( androidx.core.R.drawable.notification_bg ),
            contentDescription = "",
            modifier = Modifier.clickable {
                scope.launch {
                    scState.drawerState.open()
                }
            }
        )
        if (!isLoggedIn) {
            Icon(
                painter = painterResource( com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal ),
                contentDescription = "",
                modifier = Modifier.clickable { navController.navigate(LOGINSIGNUP_ROUTE) }
            )
        } else {
            Box {}
        }
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
            painter = painterResource(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_light),
            contentDescription = "home",
            modifier = Modifier.clickable { navController.navigate(HOME_ROUTE) }
        )
    }
}
@Composable
fun DrawerLayoutView(navController: NavHostController, scState: ScaffoldState) {

    val userVM = viewModel<UserViewModel>()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = {
                navController.navigate(PROFILE_ROUTE)
                scope.launch {
                    scState.drawerState.close()
                }
            },
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(text = "Profile page")
        }

        OutlinedButton(onClick = {
            userVM.logout()
            scope.launch {
                scState.drawerState.close()
            }
            navController.navigate(HOME_ROUTE)
        }) {
            Text(text = "Log out")
        }
    }

}
