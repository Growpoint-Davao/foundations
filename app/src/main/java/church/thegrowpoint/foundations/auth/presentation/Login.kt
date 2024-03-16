package church.thegrowpoint.foundations.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import church.thegrowpoint.foundations.R
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme

@Composable
fun Login(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.gp_login_text_logo),
            contentDescription = null
        )
        Image(
            painter = painterResource(R.drawable.gp_login_logo),
            contentDescription = null
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FoundationsTheme {
        Login()
    }
}