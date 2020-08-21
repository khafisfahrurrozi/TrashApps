package com.example.trashapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class notification extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private trashAdapter adaptertrash;
    private DatabaseReference databaseReference,databaseReferencetrash;
    private List<getset> getsets;
    private List<typetrash> typetrashes;
//    ProgressBar mProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView=findViewById(R.id.notifview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mProgressCircle = findViewById(R.id.progress_circle);

        getsets = new ArrayList<>();
        typetrashes= new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("getset").child("other");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

//                    typetrash uploadtrash=(postSnapshot.child("typetrash")).getValue(typetrash.class);
//                    getset upload = postSnapshot.getValue(getset.class);
//                    getsets.add(upload);

                    getset value = postSnapshot.getValue(getset.class);
//                    Log.i("Mantap",value.getWeight());
//                      getsets.add(value);


//                    String upload = String.valueOf(postSnapshot.child("other").getValue());
//
//                    typetrashes.add(uploadtrash);
                }

                databaseReferencetrash=FirebaseDatabase.getInstance().getReference().child("getset").child("typetrash");
                databaseReferencetrash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot trashSpapsot:dataSnapshot.getChildren()){

                            typetrash trashvalue = trashSpapsot.getValue(typetrash.class);

                            Log.i("house",trashvalue.getHousehold());
//                            Log.i("daun",trashvalue.getOrganic());
//                                  Log.i("oto",trashvalue.getAutomotive());
//                                  Log.i("danger",trashvalue.getDanger());
//                                  Log.i("air",trashvalue.getLiquid());
//                                  Log.i("metal",trashvalue.getMetal());
//                                  Log.i("kaca",trashvalue.getGlass());
//                                  Log.i("contruct",trashvalue.getContruction());

//                                  typetrashes.add(trashvalue);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//                mProgressCircle.setVisibility(View.INVISIBLE);
                adapter = new Adapter(notification.this, getsets);

//                adaptertrash = new trashAdapter(typetrashes, notification.this);
//                MergeAdapter mergeAdapter= new MergeAdapter(adapter,adaptertrash);
//                recyclerView.setAdapter(mergeAdapter);
                                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(notification.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }
}
