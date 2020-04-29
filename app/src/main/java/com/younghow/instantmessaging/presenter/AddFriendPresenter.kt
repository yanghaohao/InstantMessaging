package com.younghow.instantmessaging.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.contract.AddFriendContract
import com.younghow.instantmessaging.data.AddFrendItem
import com.younghow.instantmessaging.data.db.IMDatabase
import com.younghow.instantmessaging.entity.User
import org.jetbrains.anko.doAsync

class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {

    val addFriendItems = mutableListOf<AddFrendItem>()

    override fun search(key: String) {
        val bmobQuery = BmobQuery<User>()
        bmobQuery.addWhereContains("username",key).addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        bmobQuery.findObjects(object : FindListener<User>(){
            override fun done(p0: MutableList<User>?, p1: BmobException?) {
                if (p1 == null) {
                    val allAddFrendItem = IMDatabase.instance.getAllContact()
                    doAsync {
                        p0?.forEach {

                            var isAdded = false
                            for (contact in allAddFrendItem){
                                if (contact.name == it.userName){
                                    isAdded = true
                                }
                            }

                           val addFriendItem  = AddFrendItem(it.userName!!,it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)
                        }
                    }
                    view.onSearchSuccess()}
                else view.onSearchFail()
            }
        })
    }
}