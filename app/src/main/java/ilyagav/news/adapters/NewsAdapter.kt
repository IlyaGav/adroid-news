package ilyagav.news.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ilyagav.news.R
import ilyagav.news.models.News


class NewsAdapter(
    private val context: Context,
    var dataset: List<News>,
    private val onClick: (News) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {

    class NewsItemViewHolder(
        private val context: Context,
        itemView: View,
        private val onClick: (News) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.newsItemTitle)
        private val image: ImageView = itemView.findViewById(R.id.newsItemImageView)
        private val card: CardView = itemView.findViewById(R.id.newsItemCard)

        private var currentItem: News? = null

        init {
            card.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(item: News) {

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(ilyagav.news.R.drawable.ic_launcher_background)
                .error(ilyagav.news.R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

            currentItem = item
            title.text = item.title
            Glide.with(context).load(item.img)
                .apply(options)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        return NewsItemViewHolder(context, adapterLayout, onClick)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}