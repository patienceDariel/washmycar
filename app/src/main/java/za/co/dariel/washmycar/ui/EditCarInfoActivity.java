package za.co.dariel.washmycar.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.StitchClient;
import com.mongodb.stitch.android.auth.anonymous.AnonymousAuthProvider;
import com.mongodb.stitch.android.services.mongodb.MongoClient;

import org.bson.Document;

import java.util.List;

import za.co.dariel.washmycar.R;

/*
* Manages car information
*/

public class EditCarInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car_info);

        Button orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StitchClient stitchClient = new StitchClient(getApplicationContext(), "washmycar-ofztz");
                final MongoClient mongoClient = new MongoClient(stitchClient, "mongodb-atlas");
                final MongoClient.Database db = mongoClient.getDatabase("washmycar");

                stitchClient.logInWithProvider(new AnonymousAuthProvider()).continueWithTask(
                        new Continuation<String, Task<Document>>() {
                            @Override
                            public Task<Document> then(@NonNull Task<String> task) throws Exception {
                                final Document updateDoc = new Document(
                                        "owner_id",
                                        task.getResult()
                                );

                                updateDoc.put("number", 42);
                                return db.getCollection("orders").updateOne(null, updateDoc, true);
                            }
                        }
                ).continueWithTask(new Continuation<Document, Task<List<Document>>>() {
                    @Override
                    public Task<List<Document>> then(@NonNull Task<Document> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return db.getCollection("orders").find(
                                new Document("owner_id", stitchClient.getUserId()),
                                100
                        );
                    }
                }).addOnCompleteListener(new OnCompleteListener<List<Document>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<Document>> task) {
                        if (task.isSuccessful()) {
                            Log.d("STITCH", task.getResult().toString());
                            return;
                        }
                        Log.e("STITCH", task.getException().toString());
                    }
                });

                Intent intent = new Intent(getApplicationContext(), CheckOutActivity.class);
                startActivity(intent);
            }
        });
    }
}
