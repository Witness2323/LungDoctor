package com.sourcey.materiallogindemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.huantansheng.easyphotos.EasyPhotos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;


/**
 * Created by Belal on 1/23/2018.
 */

public class AddFragment extends Fragment {
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    popupWindow popupWindow1 = new popupWindow();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        final View view = inflater.inflate(R.layout.fragnment2, container, false);
    /*    MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                // 处理手势事件
                //继承了Activity的onTouchEvent方法，直接监听点击事件
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();
                    if(y1 - y2 > 50) {
                        Toast.makeText(view.getContext(), "向上滑", Toast.LENGTH_SHORT).show();
                    } else if(y2 - y1 > 50) {
                        Toast.makeText(view.getContext(), "向下滑", Toast.LENGTH_SHORT).show();
                    } else if(x1 - x2 > 50) {

                    } else if(x2 - x1 > 50) {

                    }
                }
            }
        };
        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);*/



        final TextFieldBoxes textFieldBoxes = view.findViewById(R.id.text_field_boxes1);
        final ExtendedEditText extendedEditText = view.findViewById(R.id.edit_text_4);
            final TextFieldBoxes HuanBing=view.findViewById(R.id.text_field_boxes5);
        HuanBing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int
                            dayOfMonth) {
                        String mouth1 = "";
                        String day1 = "";
                        if (monthOfYear < 9) {
                            mouth1 = "0" + (monthOfYear + 1);
                        } else {
                            mouth1 = String.valueOf(monthOfYear + 1);
                        }
                        if (dayOfMonth <= 9) {
                            day1 = "0" + dayOfMonth;
                        } else {
                            day1 = String.valueOf(dayOfMonth);
                        }

                        String dateStr = String.valueOf(year) + "-" + mouth1 + "-" + day1;

                        String sAgeFormat = getResources().getString(R.string.Jiuzhen);
                        sAgeFormat=String.format(dateStr);
                      extendedEditText.setText(dateStr);


                    }
                }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        ImageButton b1=(ImageButton) view.findViewById(R.id.view_back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPhotos.createAlbum(AddFragment.this, true, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.sourcey.materiallogindemo.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .setCount(1)//参数说明：最大可选数，默认1
                        .start(0);
             //   Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();

                //  FragmentManager fm;
                //  fm = getSupportFragmentManager();
                //  FragmentTransaction transaction = fm.beginTransaction();
                // transaction.show(popupWindow.newInstance()).commitAllowingStateLoss();

            }
        });

       textFieldBoxes.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {


            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                // What you want to do when text changes
            }
        });

        return view ;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




            }
    List<String> mSelected = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {

            //返回图片地址集合：如果你只需要获取图片的地址，可以用这个
            ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);
            //返回图片地址集合时如果你需要知道用户选择图片时是否选择了原图选项，用如下方法获取
            boolean selectedOriginal = data.getBooleanExtra(EasyPhotos.RESULT_SELECTED_ORIGINAL, false);

            mSelected.clear();
            mSelected.addAll(resultPaths);
        }
    }


       // public void showPopueWindow(View view) {


      /*  bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeCamera(RESULT_CAMERA_IMAGE);
                popupWindow.dismiss();

            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);

    }*/


    }
