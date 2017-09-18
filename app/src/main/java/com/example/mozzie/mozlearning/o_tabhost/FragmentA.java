package com.example.mozzie.mozlearning.o_tabhost;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

/**
 * Created by mozzie on 17/5/15.
 */

public class FragmentA extends Fragment{

    EditText editText;
    CheckBox checkBox;
    Button buttoncircle;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabhost_a, container, false);
        LOGGER.d("TabHostActivity-FragmentA", "onCreateView");
        TextView textView = (TextView) view.findViewById(R.id.aaaaaaa);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNavigationActivity.startActivity(getActivity());
            }
        });

        editText = (EditText) view.findViewById(R.id.edittext);
        editText.setKeyListener(new DigitsKeyListener(){

        });



        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editText.clearFocus();
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                }else {
                    editText.clearFocus();
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        final Animator imageViewani = new ObjectAnimator();

        imageView = (ImageView) view.findViewById(R.id.circle_image);

        final Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.cicle_press);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    float width = event.getX();
                    float height = event.getY();
                    int color = image.getPixel((int)width,(int)height);
                    int r = Color.red(color);
                    int g = Color.green(color);
                    int b = Color.blue(color);
                    int a = Color.alpha(color);
                    LOGGER.d("FragmentA-rgb", "a:" + a + " r:" + r + " g:" + g + " b:" + b);
                }
                return true;
            }
        });
        buttoncircle = (Button) view.findViewById(R.id.buttoncircle);
        buttoncircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float sum = 473826;
                float total = 3686400;
                ToastUtils.show(getContext(), ""+ ((1- sum/total) * 100) + "%");
            }
        });


        imageViewani.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGGER.d("TabHostActivity-FragmentA", "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LOGGER.d("TabHostActivity-FragmentA", "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        LOGGER.d("TabHostActivity-FragmentA", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LOGGER.d("TabHostActivity-FragmentA", "onResume");
        LOGGER.d("fragmenta-huhao", editText.getHeight() + "");
    }

    @Override
    public void onPause() {
        super.onPause();
        LOGGER.d("TabHostActivity-FragmentA", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LOGGER.d("TabHostActivity-FragmentA", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LOGGER.d("TabHostActivity-FragmentA", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGGER.d("TabHostActivity-FragmentA", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LOGGER.d("TabHostActivity-FragmentA", "onDetach");
    }
}
