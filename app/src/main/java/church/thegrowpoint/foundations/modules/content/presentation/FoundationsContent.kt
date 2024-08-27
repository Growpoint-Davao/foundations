package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.modules.auth.presentation.AuthViewModel
import church.thegrowpoint.foundations.modules.content.Sections
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ChurchViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.ContentViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DevotionViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.DiscipleshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.IdentityViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.LordshipViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.PowerViewModel
import church.thegrowpoint.foundations.modules.content.presentation.viewmodels.SalvationViewModel
import church.thegrowpoint.foundations.ui.composables.CenteredTopAppBar
import church.thegrowpoint.foundations.ui.composables.GrowpointTitlePanel
import church.thegrowpoint.foundations.ui.composables.NavigationDrawerItemWithProgress
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoundationsContent(
    contentViewModel: ContentViewModel,
    authViewModel: AuthViewModel,
    salvationViewModel: SalvationViewModel = hiltViewModel(),
    lordShipViewModel: LordshipViewModel = hiltViewModel(),
    identityViewModel: IdentityViewModel = hiltViewModel(),
    powerViewModel: PowerViewModel = hiltViewModel(),
    devotionViewModel: DevotionViewModel = hiltViewModel(),
    churchViewModel: ChurchViewModel = hiltViewModel(),
    discipleshipViewModel: DiscipleshipViewModel = hiltViewModel()
) {
    // drawer must be initially closed
    val navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationDrawerScope = rememberCoroutineScope()

    // drawer selected status state
    val navUIState = contentViewModel.navigationUIState.collectAsState()

    ModalNavigationDrawer(
        drawerContent = {
            // make sure we display correct logo for light and dark mode
            var logoPainterResource = painterResource(R.drawable.gp_login_logo)
            if (!isSystemInDarkTheme()) {
                logoPainterResource = painterResource(R.drawable.gp_login_logo_light)
            }

            ModalDrawerSheet {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    GrowpointTitlePanel(
                        modifier = Modifier.padding(16.dp),
                        title = stringResource(R.string.foundations),
                        subTitle = stringResource(R.string.established_for_growing),
                        logo = logoPainterResource
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                HorizontalDivider()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState()
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Sections.entries.forEach { section ->
                            val selected = when (section) {
                                Sections.GETTING_STARTED -> navUIState.value.gettingStartedSelected
                                Sections.SALVATION -> navUIState.value.salvationSelected
                                Sections.LORDSHIP -> navUIState.value.lordshipSelected
                                Sections.IDENTITY -> navUIState.value.identitySelected
                                Sections.POWER -> navUIState.value.powerSelected
                                Sections.DEVOTION -> navUIState.value.devotionSelected
                                Sections.CHURCH -> navUIState.value.churchSelected
                                Sections.DISCIPLESHIP -> navUIState.value.discipleshipSelected
                            }

                            val onSelected = when (section) {
                                Sections.GETTING_STARTED -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            gettingStartedSelected = true
                                        )
                                    }
                                }

                                Sections.SALVATION -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            salvationSelected = true
                                        )
                                    }
                                }

                                Sections.LORDSHIP -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            lordshipSelected = true
                                        )
                                    }
                                }

                                Sections.IDENTITY -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            identitySelected = true
                                        )
                                    }
                                }

                                Sections.POWER -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            powerSelected = true
                                        )
                                    }
                                }

                                Sections.DEVOTION -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            devotionSelected = true
                                        )
                                    }
                                }

                                Sections.CHURCH -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            churchSelected = true
                                        )
                                    }
                                }

                                Sections.DISCIPLESHIP -> {
                                    {
                                        contentViewModel.setNavigationDrawerItemSelected(
                                            discipleshipSelected = true
                                        )
                                    }
                                }
                            }

                            NavigationDrawerItemWithProgress(
                                title = stringResource(section.title),
                                subTitle = stringResource(section.subTitle),
                                icon = painterResource(section.icon),
                                selected = selected,
                                baseRoute = section.baseRoute,
                                navigationDrawerState = navigationDrawerState,
                                navigationDrawerScope = navigationDrawerScope,
                                onClick = onSelected
                            )
                        }

                        // other drawer items
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(8.dp))
                        NavigationDrawerItem(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                            label = {
                                Text(
                                    text = stringResource(R.string.logout),
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                                    contentDescription = stringResource(R.string.logout)
                                )
                            },
                            selected = false,
                            onClick = {
                                authViewModel.logout()
                            }
                        )
                    }
                }
            }
        },
        drawerState = navigationDrawerState
    ) {
        // keyboard controller for hiding the keyboard
        val keyboardController = LocalSoftwareKeyboardController.current
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                CenteredTopAppBar(
                    title = navUIState.value.sectionTitle,
                    scrollBehavior = scrollBehavior,
                    navIconContentDescription = stringResource(R.string.toggle_navigation_drawer)
                ) {
                    // toggle the navigation drawer
                    navigationDrawerScope.launch {
                        keyboardController?.hide()

                        navigationDrawerState.apply {
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
                    SectionsContent(
                        contentViewModel = contentViewModel,
                        salvationViewModel = salvationViewModel,
                        lordShipViewModel = lordShipViewModel,
                        identityViewModel = identityViewModel,
                        powerViewModel = powerViewModel,
                        devotionViewModel = devotionViewModel,
                        churchViewModel = churchViewModel,
                        discipleshipViewModel = discipleshipViewModel
                    )
                }
            }
        )
    }
}
