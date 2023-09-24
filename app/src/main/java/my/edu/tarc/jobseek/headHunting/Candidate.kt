package my.edu.tarc.jobseek.headHunting

import android.provider.ContactsContract.CommonDataKinds.Email

data class Candidate(
    var candidateId : Int,
    var name: String,
    var field: String,
    var experience: Int,
    var education: Array<String>,
    var skills: Array<String>,
    var location: String,
    var phoneNum: String,
    var email: String,
    )