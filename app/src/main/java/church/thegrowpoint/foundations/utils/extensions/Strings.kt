package church.thegrowpoint.foundations.utils.extensions

/**
 * Extension to validate the current string if it is an email.
 */
fun String.validEmail(): Boolean {
    val emailPattern = Regex(pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailPattern.matches(this.trim())
}

/**
 * Extension to validate if the current string / password has a length at least 8.
 */
fun String.validPasswordLength(): Boolean {
    return this.length >= 8
}
