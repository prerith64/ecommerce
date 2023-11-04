package com.example.bottomnavigation;


import static com.example.bottomnavigation.EQuires.categoryModelList;
import static com.example.bottomnavigation.EQuires.lists;
import static com.example.bottomnavigation.EQuires.loadCategories;
import static com.example.bottomnavigation.EQuires.loadFragmentData;
import static com.example.bottomnavigation.EQuires.loadedCategoriesNames;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {



    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private RecyclerView homePageRecycleView;
    private HomePageAdapter adapter;
    private FirebaseFirestore firebaseFirestore;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView=view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);



        categoryAdapter =new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        if(categoryModelList.size() == 0 ){
            loadCategories(categoryAdapter,getContext());
        }else {
            categoryAdapter.notifyDataSetChanged();
        }





/////////////////////////


        /////////// Horizontal product layout


/////////////////////////


        homePageRecycleView= view.findViewById(R.id.home_page_recyclerView);
        LinearLayoutManager testingLayoutManager =new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecycleView.setLayoutManager(testingLayoutManager);



        if(lists.size() == 0 ){
            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageModel>());
            adapter =new HomePageAdapter(lists.get(0));
            loadFragmentData(adapter,getContext(),0,"HOME");
        }else {
            adapter =new HomePageAdapter(lists.get(0));
            adapter.notifyDataSetChanged();
        }
        homePageRecycleView.setAdapter(adapter);

        /////////////////////////////////////////////
        return view;
    }


}
