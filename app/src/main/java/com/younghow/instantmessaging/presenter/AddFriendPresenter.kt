package com.younghow.instantmessaging.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.contract.AddFriendContract
import com.younghow.instantmessaging.data.AddFriendItem
import com.example.data.bean.Mine

class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {
        val bmobQuery = BmobQuery<Mine>()
        bmobQuery.addWhereContains("username",key).addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        bmobQuery.findObjects(object : FindListener<Mine>(){
            override fun done(p0: MutableList<Mine>?, p1: BmobException?) {
                if (p1 == null) {
                    val allAddFrendItem = com.example.data.db.UserDao.instance.getAllContact()
                    doAsync {
                        p0?.forEach {

                            var isAdded = false
                            for (contact in allAddFrendItem){
                                if (contact.name == it.userName){
                                    isAdded = true
                                }
                            }

                           val addFriendItem  = AddFriendItem(it.userName!!,it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)
                        }
                    }
                    view.onSearchSuccess()}
                else view.onSearchFail()
            }
        })
    }
}