package church.thegrowpoint.foundations.auth.domain.models

/**
 * # User
 *
 * The model representation of user in the domain model
 *
 * @property id the unique identifier of the user from the data source.
 * @property name the email of the user.
 * @property name the name of the user.
 */
interface User {
    val id: String
    val email: String
    val name: String

    /**
     * The string representation of this model object.
     *
     * @return the string value that represents this model
     */
    override fun toString(): String
}
