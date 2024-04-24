package church.thegrowpoint.foundations.auth.presentation

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import church.thegrowpoint.foundations.ui.theme.FoundationsTheme
import org.junit.Rule
import org.junit.Test

class LoginScreenUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun input_invalid_email() {
        composeTestRule.setContent {
            FoundationsTheme {
                LoginScreen()
            }
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("1122@nice")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsDisplayed()
    }

    @Test
    fun input_valid_email() {
        composeTestRule.setContent {
            FoundationsTheme {
                LoginScreen()
            }
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("test.tester@testing.net")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsNotDisplayed()
    }

    @Test
    fun input_too_short_password() {
        composeTestRule.setContent {
            FoundationsTheme {
                LoginScreen()
            }
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("12345")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsDisplayed()
    }

    @Test
    fun input_correct_length_password() {
        composeTestRule.setContent {
            FoundationsTheme {
                LoginScreen()
            }
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("112233445566")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsNotDisplayed()
    }

    @Test
    fun essential_composable_clickable_elements_exist() {
        composeTestRule.setContent {
            FoundationsTheme {
                LoginScreen()
            }
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
