package church.thegrowpoint.foundations.auth.data.models

import com.google.firebase.auth.FirebaseUser
import church.thegrowpoint.foundations.auth.domain.models.User as DomainUser

/**
 * *User*
 *
 * This is the model wrapper class in the data layer.
 *
 * @property firebaseUser the firebase user instance that will be wrapped my this model class
 */
class User(private val firebaseUser: FirebaseUser) : DomainUser {
    // needed properties for a domain layer user
    override val id: String
        get() = firebaseUser.uid
    override val email: String
        get() = firebaseUser.email.toString()
    override val name: String
        get() = firebaseUser.displayName.toString()

    /**
     * The string representation of this model object.
     *
     * @return the string value that represents this model
     */
    override fun toString(): String {
        return email
    }
}
