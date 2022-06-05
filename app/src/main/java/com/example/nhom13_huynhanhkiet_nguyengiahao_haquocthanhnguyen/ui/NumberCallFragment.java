package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.R;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database.CallDetailEntity;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database.CallDetailRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumberCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberCallFragment extends Fragment {
    List<CallDetailEntity> callDetailEntities;
    NumberCallAdapter adapter;
    RecyclerView rvCall;
    CallDetailRepository callDetailRepository;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NumberCallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumberCallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumberCallFragment newInstance(String param1, String param2) {
        NumberCallFragment fragment = new NumberCallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        callDetailEntities = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCall = view.findViewById(R.id.list);
        adapter = new NumberCallAdapter(callDetailEntities, callDetailRepository);
        rvCall.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rvCall.setLayoutManager(layoutManager);
        rvCall.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

    }
}