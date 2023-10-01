package my.edu.tarc.jobseek.home
import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class JobModel(
    var job_id: String? = null,
    var job_title: String? = null,
    var company_name: String? = null,
    var job_category: String? = null,
    var employment_type: String? = null,
    var work_location: String? = null,
    var state: String? = null,
    var salary: Float?= null,
    var job_responsibility: String? = null,
    var job_specialization: String? = null,
    var qualification: String?=null,
    var skills: String? = null,
    var year_of_experience: Int? = 0,
    var apply_status: String ?= null
): Serializable
