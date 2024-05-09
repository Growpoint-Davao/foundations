package church.thegrowpoint.foundations.modules.content.data

import church.thegrowpoint.foundations.modules.content.data.datasources.AssetService
import church.thegrowpoint.foundations.modules.content.data.datasources.ContentService
import church.thegrowpoint.foundations.modules.content.data.datasources.ContentServiceImplementation
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ContentServiceTest {
    private val contentPayload = """
        {
            "introduction": [
                [
                    "In every physical structure, the foundation is important. A building that has no strong foundation inevitably collapses. Likewise, a Christian that has no strong spiritual foundation is also in danger of spiritual collapse. Jesus reminds us of its importance at the end of the Sermon on the Mount.",
                    "*...Everyone then who hears these words of mine and does them will be like a wise man **who built his house on the rock**. And the rain fell, and the floods came, and the winds blew and beat on that house, but it did not fall, because it had been founded on the rock. And everyone who hears these words of mine and does not do them will be like a foolish man who **built his house on the sand**. And the rain fell, and the floods came, and the winds blew and beat against that house, and it fell, and great was the fall of it. (Matthew 7:24-27)*",
                    "In this parable, Jesus gives two kinds of foundation. One is the rock foundation; the other is the sand foundation. One can build his life on the rock by knowing and obeying the Word. Or one can build his life on the sand by simply ignoring it.",
                    "I believe that you want to start this journey because you want to build your life on the rock. And that rock is the Lord Jesus Christ. He is our foundation."
                ],
            ],
            "salvation": [
                [
                    "## The Problem",
                    "1. There is an immeasurable gap separating God and man. What is the cause of this eternal separation? (Read Isaiah 59:1-2)",
                    "*Behold, the Lord’s hand is not shortened, that it cannot save, or his ear dull, that it cannot hear; but your iniquities have made a sep- aration between you and your God, and your sins have hidden his face from you so that he does not hear. (Isaiah 59:1-2)*",
                    "INPUT_FIELD",
                    "Have you ever felt distant from God? We all have. Feeling far from God is very common. Many who sense the vast sepa- ration suppose that if they meditate harder, learn more about their religion, or just step into a religious sanctuary, they will be closer to God. But our separation from God is not just physical or intellectual so neither meditation nor knowledge or religious ritual can bring us any closer to God.",
                    "2. How does the Bible describe this separation?",
                    "*...for all have sinned and fall short of the glory of God... (Romans 3:23)*",
                    "INPUT_FIELD"
                ]
            ]
        }
    """

    @Test
    fun constructor_shouldBeAbleToParseContent() {
        val assetService = mockk<AssetService>()
        every { assetService.readContentFromAsset(ContentService.FILENAME) } returns contentPayload

        val contentService = ContentServiceImplementation(assetService)
        val gettingStarted = contentService.content?.gettingStarted?.get(0)
        val salvation = contentService.content?.salvation?.get(0)

        assertTrue(
            "In every physical structure, the foundation is important." in gettingStarted!![0]
        )

        assertTrue(
            "In this parable, Jesus gives two kinds of foundation." in gettingStarted[2]
        )

        assertEquals("## The Problem", salvation!![0])
        assertEquals("2. How does the Bible describe this separation?", salvation[5])
    }
}
