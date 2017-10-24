package com.example.hyj.friendsgo.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hyj.friendsgo.R;
import com.example.hyj.friendsgo.login.LoginActivity;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;

public class SettingFragment extends Fragment {

    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        btnLogout = (Button) view.findViewById(R.id.logout);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 用户登出
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessTokenKeeper.clear(getActivity().getApplicationContext());
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

    }


}
