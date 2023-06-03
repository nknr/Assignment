package com.neeraj.assignment.data.room.entry

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neeraj.assignment.utils.constant.DbConstant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = DbConstant.Post.TBL_NAME)
data class Post(

    @ColumnInfo(name = DbConstant.Post.ID)
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = DbConstant.Post.TITLE)
    val title: String,

    @ColumnInfo(name = DbConstant.Post.BODY)
    val body: String,

    @ColumnInfo(name = DbConstant.Post.USER_ID)
    val userId: Int
): Parcelable
