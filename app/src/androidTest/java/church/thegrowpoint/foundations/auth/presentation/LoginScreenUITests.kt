package church.thegrowpoint.foundations.auth.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import church.thegrowpoint.foundations.MainActivity
import church.thegrowpoint.foundations.TestActivity
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class LoginScreenUITests {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun setUp() {
        // this avoid blocking of coEvery
        // Similar issues:
        // https://github.com/mockk/mockk/issues/766
        // https://github.com/mockk/mockk/issues/344
        mockkStatic("kotlinx.coroutines.tasks.TasksKt")

        hiltRule.inject()
    }

    @Test
    fun input_invalid_email() {
        composeTestRule.setContent {
           LoginScreen()
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("1122@nice")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsDisplayed()
    }

    @Test
    fun input_valid_email() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("test.tester@testing.net")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsNotDisplayed()
    }

    @Test
    fun input_too_short_password() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("12345")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsDisplayed()
    }

    @Test
    fun input_correct_length_password() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("112233445566")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsNotDisplayed()
    }

    @Test
    fun essential_composable_clickable_elements_exist() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Sign in")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Forgot the password?")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Facebook")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Google")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Sign up")
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}
