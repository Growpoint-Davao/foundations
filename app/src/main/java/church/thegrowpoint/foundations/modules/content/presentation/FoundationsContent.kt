package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
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

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Foundations",
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.getting_started),
                    subTitle = stringResource(R.string.introduction)
                ) {

                }
                NavigationDrawerItemWithProgress(
                    title = stringResource(R.string.salvation),
                    subTitle = stringResource(R.string.lesson_1)
                ) {

                }
            }
        },
        drawerState = drawerState
    ) {
        // val authState by authViewModel.authState.collectAsState()
        val scope = rememberCoroutineScope()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.foundations),
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            // toggle the navigation drawer
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = stringResource(R.string.toggle_navigation_drawer)
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
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
