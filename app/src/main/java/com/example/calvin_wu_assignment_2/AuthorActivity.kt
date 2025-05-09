package com.example.calvin_wu_assignment_2

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import com.example.calvin_wu_assignment_2.databinding.ActivityAuthorBinding

class AuthorDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = ActivityAuthorBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        binding.returnToGame.setOnClickListener {
            dismiss()
        }

        binding.contactButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Contact Information")
                .setMessage("Email: cwu330@gmail.com\nPhone: 704-460-8844")
                .setPositiveButton("Close") {dialog, _ -> dialog.dismiss()}
                .show()
        }

        binding.profileRatingSlider.addOnChangeListener { _, value, _ ->
            binding.ratingLabel.text = "Rating: ${value.toInt()}"
        }

        return builder.create()
    }
}
