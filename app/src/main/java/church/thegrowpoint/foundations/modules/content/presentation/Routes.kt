package church.thegrowpoint.foundations.modules.content.presentation

/**
 * Base route names enum
 */
enum class Routes (val route: String) {
    // auth routes
    AUTH("auth"),
    LOGIN("login"),
    REGISTER("register"),
    FORGOT_PASSWORD("forgot_password"),

    // content routes
    CONTENT("content"),
    GETTING_STARTED("gettingStarted"),
    SALVATION("salvation")
}
