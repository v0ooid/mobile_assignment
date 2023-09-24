package my.edu.tarc.jobseek.headHunting

object Constants {
    fun getCandidateData():ArrayList<Candidate>{
        val candiList=ArrayList<Candidate>()
        val emp1=Candidate(1,"Chinmaya Mohapatra","IT", 5, arrayOf("A", "B"), arrayOf("A"), "Kuala lumpur", "0124931449", "e@gmail.com")
        candiList.add(emp1)
        val emp2=Candidate(2, "Ram prakash","HR", 6, arrayOf("A", "B"), arrayOf("A"), "Kuala lumpur", "0124931449", "e@gmail.com")
        candiList.add(emp2)

        return candiList
    }
}