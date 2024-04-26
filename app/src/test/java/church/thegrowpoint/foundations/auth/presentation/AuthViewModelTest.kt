package church.thegrowpoint.foundations.auth.presentation

import com.google.firebase.auth.FirebaseUser
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class AuthViewModelTest {
    private val viewModel = AuthViewModel()

    @Test
    fun authViewModel_SetCurrentUser_CurrentUserShouldBeUpdated() {
        val originalAuthState = viewModel.authState.value.currentUser

        // change the current user
        val mockedUser = mockk<FirebaseUser>()
        viewModel.setCurrentUser(mockedUser)
        val currentUser = viewModel.authState.value.currentUser

        assertNull(originalAuthState)
        assertNotNull(currentUser)
    }
}
