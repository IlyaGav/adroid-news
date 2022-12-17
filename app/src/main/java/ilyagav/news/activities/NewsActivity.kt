package ilyagav.news.activities

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import ilyagav.news.R
import ilyagav.news.models.News

class NewsActivity : AppCompatActivity() {

    private lateinit var news: News

    private val webView: WebView
        get() = findViewById(R.id.web_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowTitleEnabled(false)

        news = intent.getSerializableExtra("news") as News

        webView.loadUrl(news.url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}