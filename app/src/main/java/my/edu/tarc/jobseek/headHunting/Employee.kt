package my.edu.tarc.jobseek.headHunting


data class Employee(
    var username: String ?= null,
    var school: String ?= null,
    var levelSchool: String ?= null,
    var cGPA: String ?= null,
    var field: String ?= null,
    var experience: String ?= null,
    var location: String ?= null,
    var phoneNum: String ?= null,
    var email: String ?= null,
    )