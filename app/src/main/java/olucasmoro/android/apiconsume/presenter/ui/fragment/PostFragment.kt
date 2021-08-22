package olucasmoro.android.apiconsume.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import olucasmoro.android.apiconsume.R
import olucasmoro.android.apiconsume.databinding.FragmentHomeBinding
import olucasmoro.android.apiconsume.presenter.NO_NETWORK
import olucasmoro.android.apiconsume.presenter.ui.adapter.PostAdapter
import olucasmoro.android.apiconsume.presenter.ui.SharedViewModel
import olucasmoro.android.apiconsume.presenter.util.showError
import olucasmoro.android.apiconsume.presenter.util.verifyNetwork
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: SharedViewModel by sharedViewModel<SharedViewModel>()

    private val mAdapter: PostAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.bind(root)

        setupRecycler()
        listsObserver()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        if (!verifyNetwork()) showError(NO_NETWORK)
    }

    override fun onResume() {
        super.onResume()
        if (!verifyNetwork()) showError(NO_NETWORK)
    }

    private fun setupRecycler() {
        listViewModel.posts.value?.let { list ->
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

        listViewModel.posts.observe(viewLifecycleOwner) { listPost ->
            mAdapter.updateList(listPost)
            binding.recyclerList.adapter = mAdapter
        }
    }
}