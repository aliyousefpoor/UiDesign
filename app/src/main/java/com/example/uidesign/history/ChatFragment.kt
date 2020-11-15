package com.example.uidesign.history

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign.R
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.lang.Exception
import java.net.URI
import java.net.URISyntaxException

class ChatFragment : Fragment(), TextWatcher {


    private val IMAGE_PICK_CODE: Int = 100
    private lateinit var message: EditText
    private lateinit var send: ImageView
    private lateinit var pickImage: ImageView
    private lateinit var name: String
    private lateinit var webSocket: WebSocketClient
    private lateinit var recyclerView: RecyclerView

    private val SERVER_PATH = "ws://192.168.0.231:3000"
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateSocketConnection()

        name = requireArguments().getString("name").toString()

        initializeView(view)
    }


    private fun initiateSocketConnection() {
        val uri: URI
        try {
            uri = URI(SERVER_PATH)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            return
        }
        webSocket = object : WebSocketClient(uri) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                Log.d("mahdii", "onOpen")
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                Log.d("mahdii", "onClose")
            }

            override fun onMessage(message: String?) {
                Log.d("mahdii", "onMessage $message")
                if (message.isNullOrEmpty())
                    return
                activity?.runOnUiThread {
                    try {
                        val jsonObject = JSONObject(message)
                        jsonObject.put("isSent", false)

                        messageAdapter.addItem(jsonObject)

                        recyclerView.smoothScrollToPosition(messageAdapter.itemCount - 1)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onError(ex: Exception?) {
                Log.d("mahdii", "onError")
            }

        }
        webSocket.connect()


    }

    override fun afterTextChanged(s: Editable?) {
        val string = s.toString().trim()
        if (string.isEmpty()) {
            resetMessageEdit()
            send.setImageResource(R.drawable.send)
        } else {
            send.setImageResource(R.drawable.focus_send)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun resetMessageEdit() {
        message.removeTextChangedListener(this)
        message.setText("")

        message.addTextChangedListener(this)
    }

    private fun initializeView(view: View) {
        message = view.findViewById(R.id.msgEditText)
        send = view.findViewById(R.id.sendButton)
        pickImage = view.findViewById(R.id.pickImage)
        recyclerView = view.findViewById(R.id.chatRecyclerView)

        messageAdapter = MessageAdapter(layoutInflater, requireContext())
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        message.addTextChangedListener(this)

        send.setOnClickListener {
            if (message.text.isEmpty()) {

            } else {
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("name", name)
                    jsonObject.put("message", message.text.toString())
                    webSocket.send(jsonObject.toString())
                    jsonObject.put("isSent", true);
                    messageAdapter.addItem(jsonObject);

                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    resetMessageEdit()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        pickImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            if (galleryIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(galleryIntent, IMAGE_PICK_CODE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            try {
                val input: InputStream? =
                    requireActivity().contentResolver.openInputStream(data!!.data!!)
                val image: Bitmap = BitmapFactory.decodeStream(input)
                sendImage(image)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private fun sendImage(image: Bitmap) {
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
        val base64String: String = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)

        val jsonObject = JSONObject()
        try {
            jsonObject.put("name", name)
            jsonObject.put("message", base64String)
            webSocket.send(jsonObject.toString())

            jsonObject.put("isSent", true)
            messageAdapter.addItem(jsonObject)
            recyclerView.smoothScrollToPosition(messageAdapter.itemCount - 1)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }


}

