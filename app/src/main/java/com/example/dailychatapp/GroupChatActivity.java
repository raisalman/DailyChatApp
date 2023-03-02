package com.example.dailychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.dailychatapp.Adapters.MessagesAdapter;
import com.example.dailychatapp.Models.MessagesModel;
import com.example.dailychatapp.databinding.ActivityGroupChatActivirtyBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatActivirtyBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatActivirtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();


        binding.arrowBack.setOnClickListener(v -> {

            Intent intent = new Intent(GroupChatActivity.this,MainActivity.class);
            startActivity(intent);

        });

        database =FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        ArrayList<MessagesModel> messagesModels=new ArrayList<>();

        final MessagesAdapter adapter = new MessagesAdapter(messagesModels,this);
        binding.detaiRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.detaiRecyclerView.setLayoutManager(layoutManager);

        final String senderId=auth.getUid();




        binding.send.setOnClickListener(v -> {
            final String message=binding.etMessage.getText().toString();
            MessagesModel model = new MessagesModel(senderId,message);
            model.setTimeStamp(new Date().getTime());

            binding.etMessage.setText("");

            database.getReference().child("Group Chat")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            messagesModels.clear();
                            for(DataSnapshot  dataSnapshot1 : snapshot.getChildren())
                            {
                                MessagesModel model = dataSnapshot1.getValue(MessagesModel.class);
                                messagesModels.add(model);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            database.getReference().child("Group Chat").push()
                    .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });
        });


    }
}