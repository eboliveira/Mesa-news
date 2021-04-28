import com.github.mesa_news.data.models.New
import java.util.*
import java.util.UUID.randomUUID

object NewFactory {
    fun getList(size: Int): List<New> {
        return (0 until size).map {
            return@map New(
                title = randomUUID().toString(),
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
