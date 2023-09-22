package com.omongolefred.movieapp.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omongolefred.movieapp.model.data.artistData.KnownFor

class MoviesTypeConverters {

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun fromListToString( value: String ) : List<KnownFor> {
        return Gson().fromJson<List<KnownFor>>( value )
    }

    @TypeConverter
    fun fromStringToList(  value : List<KnownFor> ) : String {
        return Gson().toJson( value )
    }

}