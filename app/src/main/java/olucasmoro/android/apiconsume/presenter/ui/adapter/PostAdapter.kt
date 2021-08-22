package olucasmoro.android.apiconsume.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import olucasmoro.android.apiconsume.data.local.model.Post
import olucasmoro.android.apiconsume.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var mPostList: List<Post> = arrayListOf()

    class ViewHolder(
        private val binding: PostItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            with(binding) {
                textCode.text = post.title.replaceFirstChar(Char::uppercase)
                textDesc.text = post.body.replaceFirstChar(Char::uppercase)
            }
        }

        companion object {
            fun inflate(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mPostList[position])
    }

    override fun getItemCount(): Int = mPostList.size

    fun updateList(list: List<Post>) {
        mPostList = list
        notifyDataSetChanged()
    }
}