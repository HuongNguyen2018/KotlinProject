package com.hhmusic.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hhmusic.ui.activity.PlayerActivity
import com.hhmusic.utilities.InjectorUtils
import com.hhmusic.viewmodels.PlayListViewModel
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.EditText


class AddPlayListFragment (private val myActivity: PlayerActivity) : DialogFragment() {

    lateinit private var viewModel: PlayListViewModel
    lateinit private var listView: ListView
    lateinit private var adapter: ArrayAdapter<String>

    private var values: List<String> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, com.hhmusic.R.style.FullScreenDialogStyleAddToPlaylist);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewRoot = inflater.inflate(com.hhmusic.R.layout.dialog_fragment_add_playlist, container, false)

        var toolbar = viewRoot.findViewById<Toolbar>(com.hhmusic.R.id.toolbar)
        toolbar.setTitle("Add to PlayList")
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
                view -> dismiss()
        })

        values += "Create New PlayList"

        adapter = ArrayAdapter<String>(
                        myActivity!!,
                        com.hhmusic.R.layout.simple_list_item_2,
                        //android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        values
                        )

        // Assign adapter to ListView
        listView = viewRoot.findViewById(com.hhmusic.R.id.list)
        listView?.setAdapter(adapter)


        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = InjectorUtils.provideViewModelFactory(myActivity)
        viewModel = ViewModelProviders.of(myActivity, factory).get(PlayListViewModel::class.java)
        viewModel.getPlayLists().observe(myActivity, Observer {
            it?.let {
                for (i in it.indices) {
                    values += it[i].name
                }

                adapter.clear()
                adapter.addAll(values)
                adapter.notifyDataSetChanged()
            }
        })

        listView?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            // ListView Clicked item value
            val itemValue = listView?.getItemAtPosition(position) as String
            if (position == 0) {
                //Toast.makeText(myActivity, "User chose create new PlayList", Toast.LENGTH_SHORT).show()

                // get prompts.xml view
                val li = LayoutInflater.from(context)
                val promptsView = li.inflate(com.hhmusic.R.layout.prompts, null)

                val alertDialogBuilder = AlertDialog.Builder(myActivity)

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                val userInput = promptsView.findViewById(com.hhmusic.R.id.editTextDialogUserInput) as EditText

                // set dialog message
                alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Create New", DialogInterface.OnClickListener() { dialog, which ->
                        Toast.makeText(myActivity, userInput.getText(), Toast.LENGTH_SHORT).show()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener() { dialog, which ->
                        dialog.cancel()
                    })

                // create alert dialog
                val alertDialog = alertDialogBuilder.create()

                // show it
                alertDialog.show()

            } else {
                Toast.makeText(myActivity, "User chose " + values[position], Toast.LENGTH_SHORT).show()

            }
        })
    }
}