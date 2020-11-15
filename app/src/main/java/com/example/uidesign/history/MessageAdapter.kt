package com.example.uidesign.history

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64.decode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil.decode
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uidesign.R
import kotlinx.coroutines.CoroutineStart
import org.json.JSONException
import org.json.JSONObject
import java.lang.Byte.decode
import java.lang.IllegalArgumentException
import java.lang.Integer.decode
import java.net.URLDecoder.decode
import java.security.spec.PSSParameterSpec.DEFAULT
import java.util.*

class MessageAdapter(
    private val layoutInflater: LayoutInflater,
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_MESSAGE_SENT = 0
    private val TYPE_MESSAGE_RECEIVED = 1
    private val TYPE_IMAGE_SENT = 2
    private val TYPE_IMAGE_RECEIVED = 3
    private val messageList: ArrayList<JSONObject> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        val message: JSONObject = messageList[position]
        try {
            return if (message.getBoolean("isSent")) {
                if (message.has("message")) {
                    TYPE_MESSAGE_SENT
                } else {
                    TYPE_IMAGE_SENT
                }
            } else {
                if (message.has("message")) {
                    TYPE_MESSAGE_RECEIVED
                } else {
                    TYPE_IMAGE_RECEIVED
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        return when (viewType) {
            TYPE_MESSAGE_SENT -> {
                val sendMessageView: View =
                    inflater.inflate(R.layout.send_message_layout, parent, false)
                SendMessageViewHolder(sendMessageView)
            }
            TYPE_MESSAGE_RECEIVED -> {
                val receiveMessageView: View =
                    inflater.inflate(R.layout.receive_message_layout, parent, false)
                ReceiveMessageViewHolder(receiveMessageView)
            }
            TYPE_IMAGE_SENT -> {
                val sendImageView: View =
                    inflater.inflate(R.layout.send_image_layout, parent, false)
                SentImageViewHolder(sendImageView)
            }

            TYPE_IMAGE_RECEIVED -> {
                val receiveImageView: View =
                    inflater.inflate(R.layout.receive_image_layout, parent, false)
                ReceiveImageViewHolder(receiveImageView)
            }
            else -> throw IllegalArgumentException("Invalid View Type")
        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]
        try {
            if (message.getBoolean("isSent")) {
                if (message.has("message")) {
                    val sendMessageHolder: SendMessageViewHolder = holder as SendMessageViewHolder
                    sendMessageHolder.onBind(message.getString("message"))
                } else {
                    val sendImageHolder: SentImageViewHolder = holder as SentImageViewHolder
                    sendImageHolder.onBind(message.getString("image"), context)
                }
            } else {
                if (message.has("message")) {
                    val receiveMessageHolder: ReceiveMessageViewHolder =
                        holder as ReceiveMessageViewHolder
                    receiveMessageHolder.onBind(
                        message.getString("name"),
                        message.getString("message")
                    )
                } else {
                    val receiveImageHolder: ReceiveImageViewHolder =
                        holder as ReceiveImageViewHolder
                    receiveImageHolder.onBind(
                    message.getString("name"),
                    message.getString("image"), context)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private class SendMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sendMessage: TextView = itemView.findViewById(R.id.sendMessage)
        fun onBind(message: String) {
            sendMessage.text = message
        }
    }

    private class ReceiveMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageName: TextView = itemView.findViewById(R.id.messageName)
        val receiveMessage: TextView = itemView.findViewById(R.id.receivedMessage)

        fun onBind(name: String, message: String) {
            messageName.text = name
            receiveMessage.text = message
        }
    }

    class SentImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sendImage: ImageView = itemView.findViewById(R.id.sendImageView)
        fun onBind(image: String, context: Context) {
            Glide.with(context).load(image).into(sendImage)
        }
    }

    class ReceiveImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageName: TextView = itemView.findViewById(R.id.imageName)
        val receiveImage: ImageView = itemView.findViewById(R.id.receiveImageView)
        fun onBind(name: String, image: String, context: Context) {
            imageName.text = name
//            val bitmap:Bitmap =
            Glide.with(context).load(image).into(receiveImage)
        }
    }

//        private fun getBitmapFromString(image:String):Bitmap{
//val bytes : ByteArray =Base64.decode(image, CoroutineStart.DEFAULT)
//    }

    fun addItem(jsonObject: JSONObject) {
        messageList.add(jsonObject)
        notifyDataSetChanged()
    }

}