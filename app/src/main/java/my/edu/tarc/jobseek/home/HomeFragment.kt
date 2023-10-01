    package my.edu.tarc.jobseek.home

    import android.os.Bundle
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.appcompat.app.AppCompatActivity
    import androidx.fragment.app.Fragment
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.google.firebase.database.DataSnapshot
    import com.google.firebase.database.DatabaseError
    import com.google.firebase.database.FirebaseDatabase
    import com.google.firebase.database.ValueEventListener
    import androidx.appcompat.widget.SearchView
    import androidx.fragment.app.activityViewModels
    import androidx.navigation.fragment.findNavController
    import my.edu.tarc.jobseek.R


    class HomeFragment : Fragment(), MyClickListener {

        private lateinit var jobRecycleView: RecyclerView
        private lateinit var adapter: HomeAdapter
        private val jobList = mutableListOf<JobModel>()
        private val filteredJobList = mutableListOf<JobModel>()
        private val viewModel: HomeViewModel by activityViewModels()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_home, container, false)
            jobRecycleView = view.findViewById(R.id.recyclerViewJobs)
            jobRecycleView.layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(filteredJobList,this) // Initialize the adapter with filtered list
            jobRecycleView.adapter = adapter
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            // Initialize Firebase Realtime Database reference
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("Jobs")

            // Add a ValueEventListener to fetch data from Firebase
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    jobList.clear()
                    for (childSnapshot in snapshot.children) {
                        // Iterate through the children of each child
                        for (grandChildSnapshot in childSnapshot.children) {
                            try {
                                val item = grandChildSnapshot.getValue(JobModel::class.java)
                                item?.let { jobList.add(it) }
                            }catch (e : Exception){
                                Log.e("Error", e.toString())
                            }

                        }
                    }
                    filterJobList("") // Initialize with an empty query to show all data
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error reading data from Firebase: ${error.message}")
                }
            })

            val searchView = view.findViewById<SearchView>(R.id.searchJob)

            // Add a text change listener to the SearchView for dynamic searching
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Handle query submission (if needed)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val query = newText?.toLowerCase()?.trim() ?: ""
                    filterJobList(query) // Update the filtered list based on the search query
                    return true
                }
            })

        }


        private fun filterJobList(query: String) {
            filteredJobList.clear()

            // Filter the jobList based on the search query
            for (item in jobList) {
                val title = item.job_title?.toLowerCase() ?: ""
                val companyName = item.company_name?.toLowerCase() ?: ""
                val workLocation = item.work_location?.toLowerCase() ?: ""
                val emType = item.employment_type?.toLowerCase() ?: ""

                // Check if any of the properties contain the query
                if (title.contains(query) || companyName.contains(query) ||
                    workLocation.contains(query) || emType.contains(query)) {
                    filteredJobList.add(item)
                }
            }

            // Notify the adapter of the data change
            adapter.notifyDataSetChanged()
        }


        override fun onRecordClickListener(Index: Int) {
            viewModel.selectedIndex = Index
            // Set the selected job in the ViewModel
            viewModel.selectedJob = filteredJobList.getOrNull(Index)
            findNavController().navigate(R.id.action_navigationE_home_to_jobDetailEmployeeFragment4)
        }
    }