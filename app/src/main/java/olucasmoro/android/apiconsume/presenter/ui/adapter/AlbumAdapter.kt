package olucasmoro.android.apiconsume.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import olucasmoro.android.apiconsume.data.local.model.Album
import olucasmoro.android.apiconsume.databinding.AlbumItemBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var mAlbumList: List<Album> = arrayListOf()

    class ViewHolder(
        private val binding: AlbumItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            with(binding) {
                textCode.text = album.title.replaceFirstChar(Char::uppercase)
                textDesc.text = album.id.toString()
            }
        }

        companion object {
            fun inflate(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int = mAlbumList.size

    fun updateList(list: List<Album>) {
        mAlbumList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mAlbumList[position])
    }
}