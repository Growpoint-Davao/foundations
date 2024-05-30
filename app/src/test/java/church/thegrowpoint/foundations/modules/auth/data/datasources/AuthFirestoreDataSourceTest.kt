package church.thegrowpoint.foundations.modules.auth.data.datasources

import church.thegrowpoint.foundations.modules.auth.data.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AuthFirestoreDataSourceTest {
    private val mockedDb = mockk<FirebaseFirestore>()
    private val mockedUserCollection = mockk<CollectionReference>()

    @Before
    fun setUp() {
        // this avoid blocking of coEvery
        // Similar issues:
        // https://github.com/mockk/mockk/issues/766
        // https://github.com/mockk/mockk/issues/344
        mockkStatic("kotlinx.coroutines.tasks.TasksKt")
    }

    @Test
    fun storeUser_shouldBeAbleToStoreNewUser() = runTest {
        val testEmail = "foo@tester.com"
        val testDisplayName = "Foo"

        val mockedFirebaseUser = mockk<FirebaseUser>()
        every { mockedFirebaseUser.uid } returns ""
        every { mockedFirebaseUser.email } returns testEmail
        every { mockedFirebaseUser.displayName } returns testDisplayName

        val userDocRef = mockk<DocumentReference>()
        every { userDocRef.id } returns "1122334455"

        val testUser = User(mockedFirebaseUser)
        val testData = testUser.toAnyMap()
        coEvery { mockedUserCollection.add(match { it == testData }).await() } returns userDocRef
        every { mockedDb.collection("users") } returns mockedUserCollection

        val authFirestoreDataSource = AuthFirestoreDataSourceImplementation(mockedDb)
        val ref = authFirestoreDataSource.storeUser(user = testUser)
        assertTrue(ref is DocumentReference)
        assertEquals("1122334455", (ref as DocumentReference).id)
    }

    @Test
    fun storeUser_shouldBeAbleToMergeOrUpdateUser() = runTest {
        val testId = "foo112233"
        val testEmail = "foo@tester.com"
        val testDisplayName = "Foo"

        val mockedFirebaseUser = mockk<FirebaseUser>()
        every { mockedFirebaseUser.uid } returns testId
        every { mockedFirebaseUser.email } returns testEmail
        every { mockedFirebaseUser.displayName } returns testDisplayName

        val userDocRef = mockk<DocumentReference>()
        every { userDocRef.id } returns testId

        val testUser = User(mockedFirebaseUser)
        val testData = testUser.toAnyMap()
        coEvery {
            mockedUserCollection.document(
                testId
            ).set(
                match { it == testData },
                SetOptions.merge()
            )
                .await()
        } returns mockk<Void>()
        every { mockedDb.collection("users") } returns mockedUserCollection

        val authFirestoreDataSource = AuthFirestoreDataSourceImplementation(mockedDb)
        val ref = authFirestoreDataSource.storeUser(user = testUser)
        assertTrue(ref is Void)
    }
}
