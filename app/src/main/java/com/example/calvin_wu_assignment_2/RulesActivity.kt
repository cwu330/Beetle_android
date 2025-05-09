package com.example.calvin_wu_assignment_2

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import com.example.calvin_wu_assignment_2.databinding.ActivityRulesBinding

class RulesDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = ActivityRulesBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        binding.returnToGame.setOnClickListener {
            dismiss()
        }

        return builder.create()
    }
}
