package com.younghow.instantmessaging.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import androidx.core.app.TaskStackBuilder
import cn.bmob.v3.Bmob
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.*
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.ui.activity.ChatActivity

class IMApplication : Application() {

    companion object{
        lateinit var instance : IMApplication
    }

    val soundPool = SoundPool(2,AudioManager.STREAM_MUSIC,0)
    val short by lazy {
        soundPool.load(instance, R.raw.duan,0)
    }

    val long by lazy {
        soundPool.load(instance, R.raw.yulu,0)
    }

    private val messageLisenter = object :EMMessageListener{
        override fun onMessageRecalled(p0: MutableList<EMMessage>?) {

        }

        override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
        }

        override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {

        }

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            if (isForeground()){
                soundPool.play(short,1f,1f,0,0,1f)
            }else{
                soundPool.play(long,1f,1f,0,0,1f)
                showNotification(p0)
            }
        }

        override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageRead(p0: MutableList<EMMessage>?) {
        }
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT){
                contentText = (it.body as EMTextMessageBody).message
            }

            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())
            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = Notification.Builder(this)
                .setContentTitle(getString(R.string.receive_new_message))
                .setContentText(contentText)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.avatar1))
                .setSmallIcon(R.mipmap.ic_contact)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .notification
            notificationManager.notify(1,notification)
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        var options = EMOptions()
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.acceptInvitationAlways = false
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.autoTransferMessageAttachments = true
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true)
        //初始化
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true)

        Bmob.initialize(this, "0dc96b4ade466b957913e43c3539d286")

        EMClient.getInstance().chatManager().addMessageListener(messageLisenter)
    }

    private fun isForeground() : Boolean{
        val actvityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in actvityManager.runningAppProcesses){
            if (runningAppProcess.processName == packageName){
                return runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }

        return false
    }
}