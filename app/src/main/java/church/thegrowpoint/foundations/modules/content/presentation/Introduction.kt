package church.thegrowpoint.foundations.modules.content.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun Introduction(
    modifier: Modifier = Modifier.fillMaxWidth(),
) {

    val markdownContent = "*Sample*\n* Markdown\n* [Link](https://example.com)\n![Image](https://example.com/img.png)\n<a href=\"https://www.google.com/\">Google</a>"

    MarkdownText(markdown = markdownContent)
}
@Preview(showSystemUi = true)
@Composable
fun IntroductionPreview() {
    Introduction()
}
