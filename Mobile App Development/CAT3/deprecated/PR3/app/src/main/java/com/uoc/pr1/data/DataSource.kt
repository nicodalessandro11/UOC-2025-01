package com.uoc.pr1.data

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.uoc.pr1.data.model.Seminary
import com.uoc.pr1.data.model.Item
import com.uoc.pr1.data.model.User

class DataSourceException(message: String) : Exception(message) {

}


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

    open fun addSeminary(name:String, uri: Uri?) {}


    // *******************
    open fun addItem(title:String, descripton:String,uri: Uri?){}
    open fun addItem(Item: Item) {}
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

    public fun seminarRecommendation(duration:Int, skill: String, experience:Boolean):String
    {


        if(duration<20){
            return "beginner"
        }
        else if(duration>20){
            if(skill.equals("programming")){
                return "advanced"
            }
            else{

                return "beginner"
            }
        }
        else{
            if(duration>50){
                if(!experience)
                    return "intermediate"
                else
                    return "advanced"
            }
            else{
                return "beginner"
            }

        }
    }

    companion object DataSourceFactory{

        enum class DataSourceType {
            Hardcode, Room, Remote, Local,
        }

        //BEGIN-CODE-UOC-4

        // 4.1 Change the code to use the new DataSourceLocal class
        val Default = DataSourceType.Local

        /*
           a) Because both DataSourceLocal and DataSourceHardcode inherit from the base class DataSource,
              following the principle of polymorphism. This means that both classes can be used as
              objects of type Datasource.

           b) Since we are using the Factory pattern, adding more DataSource kinds won't affect the
              rest of the program. This is because the rest of the system (including the user interface)
              works with the abstract class DataSource and not with the specific implementations.

           c) Other type of local DataSource commonly used is one based in Room (a persistence library
              for SQLite for Android). This simplifies a lot the access to the database.
         */

        //BEGIN-END-UOC-4

        private var INSTANCE: DataSource? = null

        fun getDataSource(type:DataSourceType, context: Context? = null): DataSource {
            return synchronized(DataSource::class) {

                if(type==DataSourceType.Hardcode) {
                    val newInstance = INSTANCE ?: DataSourceHardcode()
                    INSTANCE = newInstance
                    newInstance
                }
                else if(type==DataSourceType.Local) {
                    val newInstance = INSTANCE ?: DataSourceLocal(context)
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