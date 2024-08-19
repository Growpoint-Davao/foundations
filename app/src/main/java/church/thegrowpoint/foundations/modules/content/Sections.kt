package church.thegrowpoint.foundations.modules.content

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import church.thegrowpoint.foundations.R

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

enum class Sections(
    @StringRes val title: Int,
    @StringRes val subTitle: Int,
    @DrawableRes val icon: Int,
    val baseRoute: String
) {
    GETTING_STARTED(
        title = R.string.getting_started,
        subTitle = R.string.introduction,
        icon = R.drawable.getting_started,
        baseRoute = Routes.GETTING_STARTED.route
    ),
    SALVATION(
        title = R.string.salvation,
        subTitle = R.string.lesson_1,
        icon = R.drawable.salvation,
        baseRoute = Routes.SALVATION.route
    ),
    LORDSHIP(
        title = R.string.lordship,
        subTitle = R.string.lesson_2,
        icon = R.drawable.lordship,
        baseRoute = Routes.LORDSHIP.route
    ),
    IDENTITY(
        title = R.string.identity,
        subTitle = R.string.lesson_3,
        icon = R.drawable.identity,
        baseRoute = Routes.IDENTITY.route
    ),
    POWER(
        title = R.string.power,
        subTitle = R.string.lesson_4,
        icon = R.drawable.power,
        baseRoute = Routes.POWER.route
    ),
    DEVOTION(
        title = R.string.devotion,
        subTitle = R.string.lesson_5,
        icon = R.drawable.devotion,
        baseRoute = Routes.DEVOTION.route
    ),
    CHURCH(
        title = R.string.church,
        subTitle = R.string.lesson_6,
        icon = R.drawable.church,
        baseRoute = Routes.CHURCH.route
    ),
    DISCIPLESHIP(
        title = R.string.discipleship,
        subTitle = R.string.lesson_7,
        icon = R.drawable.discipleship,
        baseRoute = Routes.DISCIPLESHIP.route
    )
}


enum class SkipAuthCodes (val code: Int) {
    INITIAL(2),
    SKIPPED(1),
    NOT_SKIPPED(0),
}
