package com.example.meditopitestcase.ui.firstscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.meditopitestcase.R
import com.example.meditopitestcase.databinding.FragmentFirstBinding
import com.example.meditopitestcase.util.SharedPrefUtil
import com.example.meditopitestcase.util.navigateSafe
import com.github.ajalt.timberkt.Timber


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*return inflater.inflate(R.layout.fragment_first, container, false)*/

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first, container, false
        )

        if (!SharedPrefUtil.getUserName(requireContext()).isEmpty()){
            navigateSafe(R.id.action_firstFragment_to_messageFragment)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.nickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s!!.length < 3) {
                    binding.btnContinue.isEnabled = false
                    binding.btnContinue.alpha = 0.5f
                } else {
                    binding.btnContinue.isEnabled = true
                    binding.btnContinue.alpha = 1.0f
                }
            }

        })

        binding.btnContinue.setOnClickListener {
            Timber.e { " NickName: ${binding.nickname.text.toString()}" }
            SharedPrefUtil.setUserName(requireContext(),binding.nickname.text.toString())
            navigateSafe(R.id.action_firstFragment_to_messageFragment)
        }

    }
}