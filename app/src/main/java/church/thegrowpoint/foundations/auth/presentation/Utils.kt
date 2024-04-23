package church.thegrowpoint.foundations.auth.presentation

fun String.validEmail(): Boolean {
    val emailPattern = Regex(pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailPattern.matches(this.trim())
}

fun String.validPasswordLength(): Boolean {
    return this.length >= 8
}
