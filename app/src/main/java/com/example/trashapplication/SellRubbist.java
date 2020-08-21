package com.example.trashapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

public class SellRubbist extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    private static final int PICK_IMAGE_REQUEST = 109;
    ImageButton camera,galleryBtn;
    CheckBox household,automotive,glass,organic,metal,dangerous,contruction,liquid;
    typetrash typeTrash;
    Spinner weight;
    EditText maps;
    TextView output;
    Button send;
    ImageView selectedImage;
    String currentPhotoPath;
    getset sendata;
    StorageTask mUploadTask;
    DatabaseReference reff,reff2;
    ProgressBar mprogresBar;
    FirebaseFirestore fStore;
    StorageReference mStorage;
    Uri mImageUri;
    Integer i=0;
    public int requestCode;
    public int resultCode;
    @Nullable
    public Intent data;

    final String type1="Household";
    final String type2="Organic";
    final String type3="Automotive";
    final String type4="Dangerous";
    final String type5="Liquid";
    final String type6="Metal";
    final String type7="Glass";
    final String type8="Contruction";

    //    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_rubbist);

        send=findViewById(R.id.send);
        selectedImage=findViewById(R.id.image_view);
        mprogresBar=findViewById(R.id.progressBar);
        camera=findViewById(R.id.camera);
        household=findViewById(R.id.household);
        metal=findViewById(R.id.metal);
        automotive=findViewById(R.id.automotive);
        glass=findViewById(R.id.glass);
        organic=findViewById(R.id.organic);

        dangerous=findViewById(R.id.dangerous);
        contruction=findViewById(R.id.contruction);
        liquid=findViewById(R.id.liquid);
        weight=findViewById(R.id.weight);
        maps=findViewById(R.id.maps);
        galleryBtn=findViewById(R.id.gallery);



        fStore=FirebaseFirestore.getInstance();

        reff= FirebaseDatabase.getInstance().getReference().child("getset/other");
        reff2=FirebaseDatabase.getInstance().getReference().child("getset/typetrash");
        mStorage= FirebaseStorage.getInstance().getReference().child("pictures/");

        sendata =new getset();
        typeTrash=new typetrash();

//        reff2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    i=(int)dataSnapshot.getChildrenCount();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    uploadFile();

//                String u_weight= weight.getSelectedItem().toString();
//
//                sendata.setWeight(u_weight);

//                if (mUploadTask != null && mUploadTask.isInProgress()) {
//                    Toast.makeText(MainActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
//                } else {
//                    uploadFile();
//                }

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(SellRubbist.this,"Data has been send",Toast.LENGTH_LONG).show();
            }
        });


        camera.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(SellRubbist.this,"Gallery Success Checked",Toast.LENGTH_SHORT).show();
                    Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(gallery, GALLERY_REQUEST_CODE);

//                openFileChooser();
            }
        });

        String[] age_list={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        ArrayAdapter adapter=new ArrayAdapter(SellRubbist.this,android.R.layout.simple_list_item_1,age_list);
        weight.setAdapter(adapter);


    }
    public void uploadother(){

//        sendata.setMaps(maps.getText().toString().trim());

        if(household.isChecked()){
            typeTrash.setHousehold(type1);
//            reff2.setValue(typeTrash);
        }
        if(organic.isChecked()){
            typeTrash.setOrganic(type2);
//            reff2.setValue(typeTrash);
        }
        if(automotive.isChecked()){
            typeTrash.setAutomotive(type3);
//            reff2.push().setValue(typeTrash);
        }
        if(dangerous.isChecked()){
            typeTrash.setDanger(type4);
//            reff2.push().setValue(typeTrash);
        }
        if(liquid.isChecked()){
            typeTrash.setLiquid(type5);
//            reff2.push().setValue(typeTrash);
        }
        if(metal.isChecked()){
            typeTrash.setMetal(type6);
//            reff2.push().setValue(typeTrash);
        }
        if(glass.isChecked()){
            typeTrash.setGlass(type7);
//            reff2.push().setValue(typeTrash);
        }
        if(contruction.isChecked()){
            typeTrash.setContruction(type8);
//            reff2.push().setValue(typeTrash);
        }
        reff2.push().setValue(typeTrash);
//        reff.push().setValue(sendata);
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }
        else {
            dispatchTakePictureIntent();
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }else {
                Toast.makeText(this, "Camera Permission is Required to Use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public  void gohome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
//    private void uploadImageToFirebase(String name, Uri contentUri) {
//        final StorageReference image = mStorage.child("pictures/" + name);
//        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        Log.d("tag", "onSuccess: Uploaded Image URl is " + uri.toString());
//                    }
//                });
//
//                Toast.makeText(SellRubbist.this, "Image Is Uploaded.", Toast.LENGTH_SHORT).show();
////                getset upload = new getset(taskSnapshot.getStorage().getDownloadUrl().toString());
////                String uploadId = reff.push().getKey();
////                reff.child(uploadId).setValue(upload);
//                getset upload = new getset(weight.getSelectedItem().toString(),
//                        taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//                String uploadId = reff.push().getKey();
//                reff.child(uploadId).setValue(upload);
//
////                reff.push().setValue(sendata);
//            }
//
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(SellRubbist.this, "Upload Failled.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
        super.onActivityResult(requestCode, resultCode, data);

                if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                File f = new File(currentPhotoPath);
                selectedImage.setImageURI(Uri.fromFile(f));
                Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mImageUri = Uri.fromFile(f);
                mediaScanIntent.setData(mImageUri);
                this.sendBroadcast(mediaScanIntent);


//                uploadImageToFirebase(f.getName(),mImageUri);



            }

        }

        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                mImageUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp +"."+getFileExt(mImageUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " +  imageFileName);
                selectedImage.setImageURI(mImageUri);

//                uploadImageToFirebase(imageFileName,mImageUri);


            }

        }
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            mImageUri = data.getData();
//        }

    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorage.child(System.currentTimeMillis()
                    + "." + getFileExt(mImageUri));
                 fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    getset upload = new getset(weight.getSelectedItem().toString(),
                                    task.getResult().toString(),
                                    maps.getText().toString().trim());
                                    String uploadId = reff.push().getKey();
                                    reff.child(uploadId).setValue(upload);
                                }
                            });
                            Toast.makeText(SellRubbist.this, "Upload successful", Toast.LENGTH_LONG).show();

                            uploadother();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SellRubbist.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                   });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
