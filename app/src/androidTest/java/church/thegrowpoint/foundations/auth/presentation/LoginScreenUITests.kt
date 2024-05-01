package church.thegrowpoint.foundations.auth.presentation

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import church.thegrowpoint.foundations.TestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenUITests {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun emailField_invalid_email_input() {
        composeTestRule.setContent {
           LoginScreen()
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("1122@nice")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsDisplayed()
    }

    @Test
    fun emailField_valid_email_input() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("test.tester@testing.net")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsNotDisplayed()
    }

    @Test
    fun passwordField_too_short_password_input() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("12345")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsDisplayed()
    }

    @Test
    fun passwordField_correct_length_password_input() {
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
