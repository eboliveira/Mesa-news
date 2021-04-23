import com.github.mesa_news.data.models.New
import java.util.*

object NewFactory {
    fun getList(size: Int): List<New> {
        return (0 until size).map {
            return@map New(
                title = "a_title",
                description = "a_description",
                content = "a_content",
                author = "an_author",
                publishedAt = "a_date_string",
                highlight = false,
                url = "an_url",
                imageUrl = "an_image_url"
            )
        }
    }
}