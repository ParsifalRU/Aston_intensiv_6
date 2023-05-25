package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.recyclerview.ListFragment.Companion.FIRST_NAME
import com.example.recyclerview.ListFragment.Companion.ID
import com.example.recyclerview.ListFragment.Companion.LAST_NAME
import com.example.recyclerview.ListFragment.Companion.LIST_FRAGMENT
import com.example.recyclerview.ListFragment.Companion.PHONE

class InfoFragment : Fragment() {
    private var id: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var phone: String? = null
    var onChangeListener: OnChangeListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setText(view)
        clickListener(view)
    }

    private fun clickListener(view: View){
        view.findViewById<Button>(R.id.save_button).setOnClickListener{
            id = arguments?.getString(ID) ?: ""
            firstName = view.findViewById<EditText>(R.id.info_first_name).text.toString()
            lastName = view.findViewById<EditText>(R.id.info_second_name).text.toString()
            phone = view.findViewById<EditText>(R.id.info_phone).text.toString()
            onChangeListener?.setChangeData(id, firstName, lastName, phone)
            parentFragmentManager.popBackStack(
                LIST_FRAGMENT,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setText(view: View){
        view.findViewById<EditText>(R.id.info_first_name).setText(
            arguments?.getString(FIRST_NAME) ?: ""
        )
        view.findViewById<EditText>(R.id.info_second_name).setText(
            arguments?.getString(LAST_NAME) ?: ""
        )
        view.findViewById<EditText>(R.id.info_phone).setText(
            arguments?.getString(PHONE) ?: ""
        )
    }

    interface OnChangeListener{
        fun setChangeData(id: String?, firstName: String?, lastName: String?, phone: String?)
    }
}

