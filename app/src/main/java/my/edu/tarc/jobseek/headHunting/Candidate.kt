package my.edu.tarc.jobseek.headHunting

import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Candidate(
    var name: String ?= null,
    var school: String ?= null,
    var levelSchool: String ?= null,
    var cGPA: Float ?= null,
    var field: String ?= null,
    var experience: String ?= null,
    var location: String ?= null,
    var phoneNum: String ?= null,
    var email: String ?= null,
    )