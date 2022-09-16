package com.example.draft1;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.draft1.databinding.FragmentRegisterBinding;
import com.example.draft1.models.User;
import com.google.gson.Gson;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    SharedPreferences mPrefs;
    User user;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mPrefs = getActivity().getSharedPreferences(getString(R.string.userPref), MODE_PRIVATE);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_register_to_FirstFragment);

            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateDAta()) {
                    NavHostFragment.findNavController(RegisterFragment.this)
                            .navigate(R.id.action_register_to_Home);
                    getActivity().finish();
                }

            }
        });
    }

    private boolean validateDAta() {
        String email = binding.editTextTextEmailAddress.getText().toString();
        String name = binding.editTextTextPersonName.getText().toString();
        String password = binding.editTextTextPassword2.getText().toString();
        String confirmPassword = binding.editTextTextPassword3.getText().toString();

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {

            Toast.makeText(getContext(), "All fields are Required.", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Email not valid.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6 || confirmPassword.length() < 6) {
            Toast.makeText(getContext(), "password must be 6 characters long.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "password should match confirm password.", Toast.LENGTH_SHORT).show();
            return false;
        }

        user = new User(name, email, password);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(getString(R.string.userdata), json);
        prefsEditor.commit();

        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
