package com.neeraj.assignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.neeraj.assignment.R
import com.neeraj.assignment.data.room.entry.Post
import com.neeraj.assignment.databinding.FragmentPostDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initResource(inflater, container)
        return binding.root
    }

    private fun initResource(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false)
        binding.model = arguments?.getParcelable(POST)
        binding.lifecycleOwner = this

        (activity as? MainActivity)?.initActionBar(getString(R.string.post_detail),true)
    }

    companion object {
        private const val POST = "post"
        fun newInstance(post: Post) =
            PostDetailFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(POST, post)
                arguments = bundle
            }
    }
}