package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.composables.GrowpointTitlePanel
import church.thegrowpoint.foundations.ui.composables.NavigationDrawerItemWithProgress
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoundationsContent(
    authViewModel: AuthViewModel,
    navController: NavHostController = rememberNavController()
) {
    // drawer must be initially closed
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationDrawerScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    GrowpointTitlePanel(
                        modifier = Modifier.padding(16.dp),
                        title = stringResource(R.string.foundations),
                        subTitle = stringResource(R.string.established_for_growing),
                        logo = painterResource(R.drawable.gp_login_logo)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.getting_started),
                    subTitle = stringResource(R.string.introduction)
                ) {
                    // close the drawer
                    navigationDrawerScope.launch {
                        drawerState.apply { close() }
                    }
                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.salvation),
                    subTitle = stringResource(R.string.lesson_1)
                ) {

                    // close the drawer
                    navigationDrawerScope.launch {
                        drawerState.apply { close() }
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        // val authState by authViewModel.authState.collectAsState()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                CenteredTopAppBar(
                    title = stringResource(R.string.foundations),
                    scrollBehavior = scrollBehavior
                ) {
                    // toggle the navigation drawer
                    navigationDrawerScope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Content(modifier = Modifier.fillMaxSize(), navController = navController)
                }
            }
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // TODO: resolve start destination
    val destination = Routes.GETTING_STARTED.name

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = destination
    ) {
        composable(route = Routes.GETTING_STARTED.name) {
            Introduction()
        }
    }
}
