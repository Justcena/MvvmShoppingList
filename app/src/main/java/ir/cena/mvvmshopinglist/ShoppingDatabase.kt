package ir.cena.mvvmshopinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class], version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao


    companion object {
        @Volatile // make this object (instance) visibile for all threads and this cause the doesnt make a any thread
        private var instance: ShoppingDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ShoppingDatabase::class.java,
            "ShopingDb.db"
        ).build()
    }
}