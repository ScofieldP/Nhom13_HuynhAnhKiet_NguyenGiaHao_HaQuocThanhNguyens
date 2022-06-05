package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.R;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database.CallDetailEntity;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database.CallDetailRepository;

import java.io.File;
import java.util.List;

public class NumberCallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder > {
    List<CallDetailEntity> callDetailEntities;
    CallDetailRepository callDetailRepository;

    public NumberCallAdapter(List<CallDetailEntity> callDetailEntities, CallDetailRepository callDetailRepository) {
        this.callDetailEntities = callDetailEntities;
        this.callDetailRepository = callDetailRepository;
    }

    public class ViewHolderCall extends RecyclerView.ViewHolder {
        TextView itemNumber, itemContent;
        LinearLayout idContainer;
        public ViewHolderCall(@NonNull View itemView) {
            super(itemView);
            itemNumber = itemView.findViewById(R.id.item_number);
            itemContent = itemView.findViewById(R.id.content);
            idContainer = itemView.findViewById(R.id.container);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new ViewHolderCall(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CallDetailEntity callDetailEntity = callDetailEntities.get(position);
        ViewHolderCall viewHolderCall =  (ViewHolderCall) holder;
        viewHolderCall.itemNumber.setText(callDetailEntity.getPhoneNumber()+ (!callDetailEntity.getContactName().isEmpty() ?"-" +callDetailEntity.getContactName():""));
        viewHolderCall.itemContent.setText(callDetailEntity.getTime()+"-"+callDetailEntity.getDate());

        viewHolderCall.idContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                File file = new File (callDetailEntity.getRecordPath());
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(Uri.fromFile(file), "audio/*");
                viewHolderCall.itemView.getContext().startActivity(intent);
                

            }
        });

    }
    private void removeItem(CallDetailEntity callDetailEntity){
        int position =callDetailEntities.indexOf(callDetailEntity);
        callDetailEntities.remove(callDetailEntity);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return callDetailEntities.size();
    }
}
