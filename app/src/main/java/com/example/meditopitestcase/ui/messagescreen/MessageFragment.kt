package com.example.meditopitestcase.ui.messagescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.meditopitestcase.R
import com.example.meditopitestcase.api.model.Message
import com.example.meditopitestcase.data.Status
import com.example.meditopitestcase.databinding.FragmentMessageBinding
import com.example.meditopitestcase.ui.messagescreen.adapter.MessageAdapter
import com.example.meditopitestcase.util.SharedPrefUtil
import com.example.meditopitestcase.util.sendMessage
import com.github.ajalt.timberkt.Timber
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MessageFragment : Fragment() {

    private val viewModel: MessageViewModel by viewModels()
    lateinit var binding: FragmentMessageBinding
    lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.nickname = SharedPrefUtil.getUserName(requireContext())

        viewModel.fetchMessage().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    Timber.e {
                        "Loading..."
                    }
                }
                Status.SUCCESS -> {
                    Timber.e { "Success ${it.data!!.messages[0].text}" }
                    adapter = MessageAdapter(it.data!!.messages as ArrayList<Message>,requireContext())
                    binding.rvChat.adapter = adapter
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT)
                        .show()
                    Timber.e { "$it.message" }
                }
            }
        })


        binding.btnSendMessage.setOnClickListener {
            val message = sendMessage(binding.nickname.toString(), binding.etMessage.text.toString())
            adapter.addItem(message)
            binding.rvChat.scrollToPosition(adapter.itemCount - 1)
            binding.etMessage.text.clear()

            Timber.e {
                "$message"
            }
        }

        binding.btnLeave.setOnClickListener {
            SharedPrefUtil.setUserName(requireContext(),"")
            activity!!.onBackPressed()
        }
    }
}