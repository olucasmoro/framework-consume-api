package olucasmoro.android.apiconsume.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import olucasmoro.android.apiconsume.R
import olucasmoro.android.apiconsume.databinding.FragmentHomeBinding
import olucasmoro.android.apiconsume.presenter.ui.SharedViewModel
import olucasmoro.android.apiconsume.presenter.ui.adapter.TodoAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodoFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: SharedViewModel by sharedViewModel<SharedViewModel>()

    private val mAdapter: TodoAdapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.bind(root)

        setupRecycler()
        listsObserver()
        return binding.root
    }

    private fun setupRecycler() {
        listViewModel.todos.value?.let { list ->
            mAdapter.updateList(list)
            binding.recyclerList.adapter = mAdapter
        }
    }

    private fun listsObserver() {
        listViewModel.spinner.observe(viewLifecycleOwner) { value ->
            value.let { show ->
                binding.progressListCurrency.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        listViewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.rootLayout, text, Snackbar.LENGTH_SHORT).show()
                listViewModel.onSnackbarShown()
            }
        }

        listViewModel.todos.observe(viewLifecycleOwner) { listTodo ->
            mAdapter.updateList(listTodo)
            binding.recyclerList.adapter = mAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}