package com.example.draft1.ui.contribute;

import static android.app.Activity.RESULT_OK;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.draft1.databinding.FragmentContributionNewBinding;

public class contributeFragment extends Fragment {

    private FragmentContributionNewBinding binding;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int count = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentContributionNewBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        Button button = (Button) root.findViewById(binding.UploadImg.getId());
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if(count == 7)
            {
                count = 0;
            }

            switch (count) {
                case 1:
                    final ImageView imageView = binding.camImg;
                    imageView.setImageBitmap(imageBitmap);
                    break;
                case 2:
                    final ImageView imageView1 = binding.camImg1;
                    imageView1.setImageBitmap(imageBitmap);
                    break;
                case 3:
                    final ImageView imageView2 = binding.camImg2;
                    imageView2.setImageBitmap(imageBitmap);
                    break;
                case 4:
                    final ImageView imageView3 = binding.camImg3;
                    imageView3.setImageBitmap(imageBitmap);
                    break;
                case 5:
                    final ImageView imageView4 = binding.camImg4;
                    imageView4.setImageBitmap(imageBitmap);
                    break;

                case 6:
                    final ImageView imageView5 = binding.camImg5;
                    imageView5.setImageBitmap(imageBitmap);
                    break;
            }

            count++;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}