package com.example.meditopitestcase.ui.messagescreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.meditopitestcase.R
import com.example.meditopitestcase.api.model.Message
import com.example.meditopitestcase.databinding.ItemChatBinding
import com.example.meditopitestcase.databinding.ItemChatMyBinding
import com.example.meditopitestcase.util.SharedPrefUtil


class MessageAdapter(private var list: ArrayList<Message>, var context : Context) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        val inflater = LayoutInflater.from(parent.getContext())
        var binding: ViewDataBinding
        return if (viewType == VIEW_TYPE_MY_MESSAGE ){
            binding = DataBindingUtil.inflate(inflater, R.layout.item_chat_my, parent, false)
            MessageViewHolder(binding as ItemChatMyBinding)
        } else {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.item_chat, parent, false)
            MessageViewHolder(binding as ItemChatBinding)
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val animation: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.item_animation_fall_down
        )
        holder.itemView.startAnimation(animation)
        if(holder.itemViewType == VIEW_TYPE_MY_MESSAGE) {
            holder.mychatBinding.item = list[position]
        }
        else{
            holder.chatBinding.item = list[position]
        }

    }

    override fun getItemCount(): Int = list.size

    fun addItem(item: Message) {
        list.add(item)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].user.nickname == SharedPrefUtil.getUserName(context)) {
            VIEW_TYPE_MY_MESSAGE
        } else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    fun clear() {
        list = arrayListOf()
    }

    companion object {
        private const val VIEW_TYPE_MY_MESSAGE = 1
        private const val VIEW_TYPE_OTHER_MESSAGE = 2
    }


    inner class MessageViewHolder : RecyclerView.ViewHolder {
        lateinit var chatBinding: ItemChatBinding
        lateinit var mychatBinding: ItemChatMyBinding

        constructor(binding: ItemChatBinding) : super(binding.getRoot()) {
            chatBinding = binding
        }

        constructor(binding: ItemChatMyBinding) : super(binding.getRoot()) {
            mychatBinding = binding
        }
    }


}