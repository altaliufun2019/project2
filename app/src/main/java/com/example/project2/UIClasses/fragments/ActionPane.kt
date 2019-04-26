package com.example.project2.UIClasses.fragments
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.project2.R

class ActionPane : Fragment(), AdapterView.OnItemSelectedListener {
    private val listFragment = ListFragment()
    private val gridFragment = GridFragment()
    private val options = Array<String>(2) { "" }

    init {
        options[0] = "List"
        options[1] = "Grid"

    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        when (position) {
            0 -> {
//                (activity as AppCompatActivity).supportFragmentManager
                fragmentManager!!.beginTransaction().replace(R.id.cont, listFragment).commit()

            }
            1 -> {
                fragmentManager!!.beginTransaction().replace(R.id.cont, gridFragment).commit()

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentManager!!.beginTransaction().add(R.id.cont, listFragment).commit()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.action_pane, container, false)
        val spinner = view.findViewById<Spinner>(R.id.btn3)

        val arrayAdapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, options)
        spinner.onItemSelectedListener = this
        spinner.adapter = arrayAdapter



        return view

    }
}