package com.example.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.javafaker.Faker

class ListFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener, InfoFragment.OnChangeListener {

    companion object{
        const val FIRST_NAME = "FIRST_NAME"
        const val LAST_NAME = "LAST_NAME"
        const val PHONE = "PHONE"
        const val LIST_FRAGMENT = "LIST_FRAGMENT"
        const val ID = "ID"
        const val CONTACT_ARRAY = "CONTACT_ARRAY"
    }

    private var adapter: RecyclerViewAdapter? = null
    private var contactArray : ArrayList<String>?  = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            contactArray = savedInstanceState.getStringArrayList(CONTACT_ARRAY)
        }
        setRecyclerView()
    }

    private fun setRecyclerView(){
        adapter = RecyclerViewAdapter(requireContext(), setHundredContact())
        adapter!!.onItemClickListener = this
        recyclerView = requireView().findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setHundredContact(): ArrayList<String>{
        if (contactArray == null){
            contactArray = arrayListOf()
            val faker = Faker()
            var id : Int
            var firstName : String
            var secondName : String
            var phone : String
            var url : String
            for (i in 0..599){
                id = i + 1
                firstName = faker.name().firstName()
                secondName = faker.name().lastName()
                phone = faker.phoneNumber().phoneNumber()
                url = "https://loremflickr.com/320/240?random=$id"
                contactArray!!.add(id.toString())
                contactArray!!.add(firstName)
                contactArray!!.add(secondName)
                contactArray!!.add(phone)
                contactArray!!.add(url)
            }
        }
        return contactArray as ArrayList<String>
    }

    private fun infoFragmentReplace(position: Int){
        val id = contactArray?.get(0 + position * 5)
        val firstName = contactArray?.get(1 + position * 5)
        val lastName = contactArray?.get(2 + position * 5)
        val phone = contactArray?.get(3 + position * 5)
        val bundle = Bundle()
        bundle.putString(ID, id)
        bundle.putString(FIRST_NAME, firstName)
        bundle.putString(LAST_NAME, lastName)
        bundle.putString(PHONE, phone)
        val fragment = InfoFragment()
        fragment.onChangeListener = this
        fragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(LIST_FRAGMENT)
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    override fun onItemClick(position: Int) {
        infoFragmentReplace(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setChangeData(id: String?, firstName: String?, lastName: String?, phone: String?) {
        contactArray?.set(1 + (id!!.toInt() - 1) * 5, firstName.toString())
        contactArray?.set(2 + (id!!.toInt() - 1) * 5, lastName.toString())
        adapter?.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(CONTACT_ARRAY, contactArray)
    }
}