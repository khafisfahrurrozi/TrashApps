package com.example.trashapplication.ui.profil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.trashapplication.Login;
import com.example.trashapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilFragment extends Fragment {

    FirebaseUser user;
    private ProfilViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ProfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        final Button logout;



        final TextView textView = root.findViewById(R.id.text_profil);
        logout=root.findViewById(R.id.logout);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getActivity(), Login.class));

//
//                final EditText resetPassword = new EditText(v.getContext());
//
//                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
//                passwordResetDialog.setTitle("Reset Password ?");
//                passwordResetDialog.setMessage("Enter New Password > 6 Characters long.");
//                passwordResetDialog.setView(resetPassword);
//
//                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // extract the email and send reset link
//                        String newPassword = resetPassword.getText().toString();
//                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(getActivity(), "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getActivity(), "Password Reset Failed.", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//
//                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // close
//                    }
//                });
//
//                passwordResetDialog.create().show();
//
            }
        });

        return root;
    }

}
