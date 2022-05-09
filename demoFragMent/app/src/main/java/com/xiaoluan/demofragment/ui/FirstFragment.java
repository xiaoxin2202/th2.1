package com.xiaoluan.demofragment.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.xiaoluan.demofragment.R;
import com.xiaoluan.demofragment.data.db.SQLiteHelper;
import com.xiaoluan.demofragment.data.model.Item;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    RecyclerView recyclerView;
    List<Item> itemList;
    ItemAdapter adapter;

    public FirstFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate FirstFragment","true");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView","true");
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = v.findViewById(R.id.rv_item);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("onCreatedView","true");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","true");
        initView();
    }

    private void initView() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
        itemList = sqLiteHelper.getAll();
        adapter = new ItemAdapter(getContext(), itemList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void onItemAdded(Item item) {
        itemList.add(item);
        adapter.notifyItemInserted(itemList.size() - 1);
        recyclerView.smoothScrollToPosition(itemList.size());
    }
}