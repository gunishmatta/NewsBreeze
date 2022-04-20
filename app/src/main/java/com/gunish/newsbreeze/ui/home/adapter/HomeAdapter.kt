import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunish.newsbreeze.R
import com.gunish.newsbreeze.data.local.entity.News
import com.gunish.newsbreeze.databinding.ItemNewsBinding
import com.gunish.newsbreeze.utils.CommonUtils

class HomeAdapter(val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var list = emptyList<News>()
    var onItemClick: ((pos: String, model: News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return HomeAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val model = list[position]
        with(holder){

            binding.textTitle.text = model.Title
            binding.textContent.text = model.Description
            binding.textDate.text = model.Date?.let { CommonUtils.displayTime(it) }
            Glide.with(context).load(model.Image).into(binding.imgBanner)
            if (model.Saved == 0){
                binding.btnSave.text = "Save"
                binding.rlBookmark.setBackgroundResource(R.drawable.ic_background)
                binding.imgBookMark.setImageResource(R.drawable.ic_save_outline)

            }else {
                binding.btnSave.text = "Saved"
                binding.rlBookmark.setBackgroundResource(R.drawable.corner_primary)
                binding.imgBookMark.setImageResource(R.drawable.ic_save)


            }
            binding.rlBookmark.setOnClickListener {
                onItemClick!!.invoke("Save", model)
            }
            binding.btnSave.setOnClickListener {
                onItemClick!!.invoke("Save", model)
            }
            binding.consParent.setOnClickListener {
                onItemClick!!.invoke("Read", model)
            }
            binding.btnRead.setOnClickListener {
                onItemClick!!.invoke("Read", model)

            }

        }
    }



    internal fun setNews(words: List<News>) {
        this.list = words
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemNewsBinding.bind(itemView)
    }

}