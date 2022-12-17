package ilyagav.news.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import ilyagav.news.R
import ilyagav.news.adapters.NewsAdapter
import ilyagav.news.datasources.NewsDataSource
import ilyagav.news.models.News

class ListNewsActivity : AppCompatActivity() {

    val newsList: RecyclerView
        get() = findViewById(R.id.list_news)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)
        val adapter = NewsAdapter(this, listOf()) { item ->
            onClick(item)
        }
        newsList.adapter = adapter

        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val country = parent?.getItemAtPosition(position).toString();

                NewsDataSource().loadItems(country) { news ->
                    run {
                        adapter.dataset = news
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        }

    }


    private fun onClick(news: News) {
        val intent = Intent(this, NewsActivity::class.java)
        intent.putExtra("news", news)
        startActivity(intent)
    }
}