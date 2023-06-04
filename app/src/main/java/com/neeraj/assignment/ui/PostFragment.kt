package com.neeraj.assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neeraj.assignment.R
import com.neeraj.assignment.data.remote.Resource
import com.neeraj.assignment.databinding.FragmentPostBinding
import com.neeraj.assignment.ui.adapter.PostAdapter
import com.neeraj.assignment.utils.AppUtils
import com.neeraj.assignment.utils.constant.FragmentTag
import com.neeraj.assignment.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private val viewModel: PostViewModel by viewModels()
    private val adapter: PostAdapter by lazy { PostAdapter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        initResource(inflater, container)
        initObserver()
        return binding.root
    }

    private fun initResource(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        (activity as? MainActivity)?.initActionBar(getString(R.string.post))

        binding.postRecyclerView.adapter = adapter
        binding.postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setItemClickListener {
            AppUtils.replaceFragment(requireActivity(), PostDetailFragment.newInstance(it), FragmentTag.POST_DETAIL)
        }
    }

    private fun initObserver() {
        viewModel.postListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> Log.e(TAG, "postListLiveData.observe() error: ${it.throwable}")
                is Resource.Success -> adapter.setItemList(it.data)
            }
        }
    }

    companion object {
        fun newInstance() = PostFragment()
        private const val TAG = "PostFragment"
    }
}