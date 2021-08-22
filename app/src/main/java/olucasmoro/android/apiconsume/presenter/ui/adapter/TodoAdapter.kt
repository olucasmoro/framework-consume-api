package olucasmoro.android.apiconsume.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import olucasmoro.android.apiconsume.data.local.model.Todo
import olucasmoro.android.apiconsume.databinding.TodoItemBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var mTodoList: List<Todo> = arrayListOf()

    class ViewHolder(
        private val binding: TodoItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            with(binding) {
                textCode.text = todo.title.replaceFirstChar(Char::uppercase)
                if (todo.completed) {
                    textDesc.text = "Completed"
                } else {
                    textDesc.text = "Incomplete"
                }
            }
        }

        companion object {
            fun inflate(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mTodoList[position])
    }

    override fun getItemCount(): Int = mTodoList.size

    fun updateList(list: List<Todo>) {
        mTodoList = list
        notifyDataSetChanged()
    }
}