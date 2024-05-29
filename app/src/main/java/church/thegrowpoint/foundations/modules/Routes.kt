package church.thegrowpoint.foundations.modules

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
    SALVATION("salvation"),
    LORDSHIP("lordship"),
    IDENTITY("identity"),
    POWER("power"),
    DEVOTION("devotion"),
    CHURCH("church"),
    DISCIPLESHIP("discipleship"),
}

enum class SkipAuthCodes (val code: Int) {
    INITIAL(2),
    SKIPPED(1),
    NOT_SKIPPED(0),
}
