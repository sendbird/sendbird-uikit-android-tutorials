package com.sendbird.uikit.tutorial

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sendbird.android.channel.GroupChannel
import com.sendbird.android.exception.SendbirdException
import com.sendbird.android.params.GroupChannelCreateParams
import com.sendbird.android.user.User
import com.sendbird.uikit.SendbirdUIKit
import com.sendbird.uikit.activities.ChannelActivity

class OrderStatusActivity : AppCompatActivity() {
    val orderDetails = OrderDetails(
        "12345",
        "In transit 🚛",
        listOf("Pizza", "Coke"),
        "" // TODO: Replace with the actual delivery person ID, DELIVERY_PERSON_ID
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_status)
        findViewById<TextView>(R.id.orderTitle).text = "Order #${orderDetails.orderNumber}"
        findViewById<TextView>(R.id.orderDetails).text = "Details:\n${orderDetails.orderItems.joinToString("\n- ")}"
        findViewById<TextView>(R.id.orderStatus).text = "Status: ${orderDetails.orderStatus}"
        findViewById<View>(R.id.chat_button).setOnClickListener {
            SendbirdUIKit.connect { user: User?, e: SendbirdException? ->
                if (user == null) {
                    // Handle error using e
                    return@connect
                }

                val params = GroupChannelCreateParams()
                params.apply {
                    userIds = listOf(user.userId, orderDetails.deliveryPersonId)
                    isDistinct = true // If true, the channel will be created only if it doesn't exist
                }

                GroupChannel.createChannel(params) { channel, e ->
                    if (channel != null) {
                        val intent = ChannelActivity.newIntent(this@OrderStatusActivity, channel.url)
                        startActivity(intent)
                    }
                    e?.printStackTrace()
                }
            }
        }

    }
}
