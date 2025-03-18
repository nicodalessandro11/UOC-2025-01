package com.uoc.pr1.data

import androidx.lifecycle.MutableLiveData
import com.uoc.pr1.data.model.Seminary
import com.uoc.pr1.data.model.Item
import com.uoc.pr1.data.model.User

open class  DataSource {

    protected lateinit var ItemsLiveData: MutableLiveData<MutableList<Item>>
    protected lateinit var seminarsLiveData: MutableLiveData<MutableList<Seminary>>

    open fun login(username: String, password: String): Result<User>?{
        return null
    }
    open fun logout(){}

    // *********************

    open fun selectSeminarssUser(user_id:Int){

    }

    fun getSeminars():MutableLiveData<MutableList<Seminary>>? {

        return seminarsLiveData
    }

    // *******************
    open fun addItem(Item: Item){}
    open fun removeItem(Item: Item){}
    open fun getItemForId(id: Long): Item?{
        return null
    }

    fun getCorrects(answers:Array<Int>):Int
    {
        var result:Int = 0
        var pos:Int = 0
        for (item: Item in ItemsLiveData?.value!!) {
            if(item.correct_answer==answers[pos]){
                result++;

            }
            pos++;
        }


        return result
    }

    fun getItemPos(pos:Int):Item
    {
        return ItemsLiveData?.value!![pos]
    }

    fun getCountItems():Int
    {
        return ItemsLiveData?.value!!.count()
    }

    fun getItemList():MutableLiveData<MutableList<Item>>?{
        return ItemsLiveData
    }


    open fun selectItemsSeminary(seminary_id:Int){

    }

    companion object DataSourceFactory{

        enum class DataSourceType {
            Hardcode, Room, Remote
        }

        val Default = DataSourceType.Hardcode

        private var INSTANCE: DataSource? = null

        fun getDataSource(type:DataSourceType): DataSource {
            return synchronized(DataSource::class) {

                if(type==DataSourceType.Hardcode) {
                    val newInstance = INSTANCE ?: DataSourceHardcode()
                    INSTANCE = newInstance
                    newInstance
                }
                else{
                    val newInstance = INSTANCE ?: DataSourceHardcode()
                    INSTANCE = newInstance
                    newInstance
                }
            }
        }
    }

}