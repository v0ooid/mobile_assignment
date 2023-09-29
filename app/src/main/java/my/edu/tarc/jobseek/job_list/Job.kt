package my.edu.tarc.jobseek.job_list

data class Job(
    var company_name:String?=null,
    var job_title: String?=null,
    var job_specialization: String?=null,
    var employment_type: String?=null,
    var job_responsibility:String?=null,
    var skills:String?=null,
    var qualification:String?=null,
    var year_of_experience:Int?=0,
    var salary: Float?=null
)
