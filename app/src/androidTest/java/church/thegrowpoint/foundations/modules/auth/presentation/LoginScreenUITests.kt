package church.thegrowpoint.foundations.modules.auth.presentation

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import church.thegrowpoint.foundations.TestActivity
import church.thegrowpoint.foundations.modules.auth.presentation.LoginScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// TODO: Rewrite tests as proper UI and instrumentation test
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

    /**
     * Test email field if it will display a invalid email label when it is supplied with invalid email.
     */
    @Test
    fun emailField_invalidEmail_shouldDisplayInvalidEmailLabel() {
        composeTestRule.setContent {
           LoginScreen()
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("1122@nice")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsDisplayed()
    }

    /**
     * Test email field if it will not display any error label when email is valid
     */
    @Test
    fun emailField_validEmail_shouldNotDisplayInvalidEmailLabel() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Email")
            .performTextInput("test.tester@testing.net")

        composeTestRule.onNodeWithText("Please provide a valid email")
            .assertIsNotDisplayed()
    }

    /**
     * Test password field if it will display short password label when password is too short.
     */
    @Test
    fun passwordField_shortPassword_shouldDisplayShortPasswordLabel() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("12345")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsDisplayed()
    }

    /**
     * Test password field if it will not display short password label when password length is correct.
     */
    @Test
    fun passwordField_correctLengthPassword_shouldNotDisplayShortPasswordLabel() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Password")
            .performTextInput("112233445566")

        composeTestRule.onNodeWithText("Password is too short")
            .assertIsNotDisplayed()
    }

    @Test
    fun skipSignInButton_shouldShowDialogBoxToConfirmSkippingOfAuthentication() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithText("Skip sign in")
            .performClick()

        // assert dialog box has appeared
        composeTestRule.onNodeWithText("Skip Authentication?")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("If you are not authenticated, all your answers will not be stored to the cloud, and will not sync across devices.")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Yes")
            .assertIsDisplayed()

        // click cancel
        composeTestRule.onNodeWithText("No")
            .performClick()

        // dialog box should not be present anymore
        composeTestRule.onNodeWithText("Skip authentication?")
            .assertIsNotDisplayed()
    }

    /**
     * TODO: Slowly replace this with actual instrumentation tests
     */
    @Test
    fun requiredComposeElements() {
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

        composeTestRule.onNodeWithText("Skip sign in")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithText("Sign up")
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}
