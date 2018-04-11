package com.raqust.bluko.common.message.receiver

import android.annotation.SuppressLint
import android.content.Context
import android.os.Message
import android.text.TextUtils
import android.util.Log
import com.raqust.bluko.R
import com.xiaomi.mipush.sdk.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by linzehao
 * time: 2018/4/11.
 * info: https://dev.mi.com/console/doc/detail?pId=41
 */
class XiaomiImpReceiver : PushMessageReceiver() {

    private val TAG = "XiaomiImpReceiver"

    private var mRegId: String? = null
    private var mTopic: String? = null
    private var mAlias: String? = null
    private var mAccount: String? = null
    private var mStartTime: String? = null
    private var mEndTime: String? = null

    private val simpleDate: String
        @SuppressLint("SimpleDateFormat")
        get() = SimpleDateFormat("MM-dd hh:mm:ss").format(Date())

    override fun onReceivePassThroughMessage(context: Context, message: MiPushMessage) {
        Log.v(TAG, "onReceivePassThroughMessage is called. " + message.toString())
        val log = context.getString(R.string.recv_passthrough_message, message.content)

        if (!TextUtils.isEmpty(message.topic)) {
            mTopic = message.topic
        } else if (!TextUtils.isEmpty(message.alias)) {
            mAlias = message.alias
        }
        Log.v(TAG, log)
    }

    override fun onNotificationMessageClicked(context: Context, message: MiPushMessage) {
        Log.v(TAG,"onNotificationMessageClicked is called. " + message.toString())
        val log = context.getString(R.string.click_notification_message, message.content)

        if (!TextUtils.isEmpty(message.topic)) {
            mTopic = message.topic
        } else if (!TextUtils.isEmpty(message.alias)) {
            mAlias = message.alias
        }

        val msg = Message.obtain()
        if (message.isNotified) {
            msg.obj = log
        }
        Log.v(TAG,log)
    }

    override fun onNotificationMessageArrived(context: Context, message: MiPushMessage) {
        Log.v(TAG,
                "onNotificationMessageArrived is called. " + message.toString())
        val log = context.getString(R.string.arrive_notification_message, message.content)

        if (!TextUtils.isEmpty(message.topic)) {
            mTopic = message.topic
        } else if (!TextUtils.isEmpty(message.alias)) {
            mAlias = message.alias
        }

        val msg = Message.obtain()
        msg.obj = log
        Log.v(TAG,log)
    }

    override fun onCommandResult(context: Context, message: MiPushCommandMessage) {
        Log.v(TAG,
                "onCommandResult is called. " + message.toString())
        val command = message.command
        val arguments = message.commandArguments
        val cmdArg1 = if (arguments != null && arguments.size > 0) arguments[0] else null
        val cmdArg2 = if (arguments != null && arguments.size > 1) arguments[1] else null
        val log: String
        if (MiPushClient.COMMAND_REGISTER == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1
                log = context.getString(R.string.register_success)
            } else {
                log = context.getString(R.string.register_fail)
            }
        } else if (MiPushClient.COMMAND_SET_ALIAS == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mAlias = cmdArg1
                log = context.getString(R.string.set_alias_success, mAlias)
            } else {
                log = context.getString(R.string.set_alias_fail, message.reason)
            }
        } else if (MiPushClient.COMMAND_UNSET_ALIAS == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mAlias = cmdArg1
                log = context.getString(R.string.unset_alias_success, mAlias)
            } else {
                log = context.getString(R.string.unset_alias_fail, message.reason)
            }
        } else if (MiPushClient.COMMAND_SET_ACCOUNT == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mAccount = cmdArg1
                log = context.getString(R.string.set_account_success, mAccount)
            } else {
                log = context.getString(R.string.set_account_fail, message.reason)
            }
        } else if (MiPushClient.COMMAND_UNSET_ACCOUNT == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mAccount = cmdArg1
                log = context.getString(R.string.unset_account_success, mAccount)
            } else {
                log = context.getString(R.string.unset_account_fail, message.reason)
            }
        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mTopic = cmdArg1
                log = context.getString(R.string.subscribe_topic_success, mTopic)
            } else {
                log = context.getString(R.string.subscribe_topic_fail, message.reason)
            }
        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mTopic = cmdArg1
                log = context.getString(R.string.unsubscribe_topic_success, mTopic)
            } else {
                log = context.getString(R.string.unsubscribe_topic_fail, message.reason)
            }
        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mStartTime = cmdArg1
                mEndTime = cmdArg2
                log = context.getString(R.string.set_accept_time_success, mStartTime, mEndTime)
            } else {
                log = context.getString(R.string.set_accept_time_fail, message.reason)
            }
        } else {
            log = message.reason
        }

        val msg = Message.obtain()
        msg.obj = log
        Log.v(TAG,log)
    }

    override fun onReceiveRegisterResult(context: Context, message: MiPushCommandMessage) {
        Log.v(TAG,
                "onReceiveRegisterResult is called. " + message.toString())
        val command = message.command
        val arguments = message.commandArguments
        val cmdArg1 = if (arguments != null && arguments.size > 0) arguments[0] else null
        val log: String
        if (MiPushClient.COMMAND_REGISTER == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1
                log = context.getString(R.string.register_success)
            } else {
                log = context.getString(R.string.register_fail)
            }
        } else {
            log = message.reason
        }

        val msg = Message.obtain()
        msg.obj = log
        Log.v(TAG,log)
    }

}