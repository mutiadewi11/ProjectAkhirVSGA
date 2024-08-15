package com.example.projectakhirvsga;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectakhirvsga.repository.UserRepository;

public class AddDataAct extends AppCompatActivity {
    private EditText editName, editAddress, editNim, editTempatLahir, editTglLahir, editMasuk, editLulus, editJob, editJabatan, editTlp;
    private Button btnSubmit, btnCancel;
    private ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initalizeViews();
        initializeOnClickListeners();
    }
    void initializeOnClickListeners() {
        btnSubmit.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String domisili = editAddress.getText().toString();
            String nim = editNim.getText().toString().trim();
            String tmptlahir = editTempatLahir.getText().toString().trim();
            String tgllahir = editTglLahir.getText().toString().trim();
            String thnmasuk = editMasuk.getText().toString().trim();
            String thnlulus = editLulus.getText().toString().trim();
            String job = editJob.getText().toString().trim();
            String jbtn = editJabatan.getText().toString().trim();
            String tlp = editTlp.getText().toString().trim();

            if (name.isEmpty() || domisili.isEmpty()) {
                Toast.makeText(AddDataAct.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                insertDataToSQLite(name, nim, tmptlahir, tgllahir, domisili, tlp, thnmasuk, thnlulus, job, jbtn);
                Toast.makeText(AddDataAct.this, "User data submitted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }

    void initalizeViews() {
        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editNim = findViewById(R.id.editNim);
        editTempatLahir = findViewById(R.id.editTempatLahir);
        editTglLahir = findViewById(R.id.editTglLahir);
        editMasuk = findViewById(R.id.editMasuk);
        editLulus = findViewById(R.id.editLulus);
        editJob = findViewById(R.id.editJob);
        editJabatan = findViewById(R.id.editJabatan);
        editTlp = findViewById(R.id.editTlp);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.btnBack);
    }

    void insertDataToSQLite(String name, String nim, String tempatLahir, String tanggalLahir, String alamat, String telepon, String tahunMasuk, String tahunLulus, String job, String jabatan) {
        UserRepository userRepository = new UserRepository(this);
        userRepository.open();
        userRepository.createUser(name, nim, tempatLahir, tanggalLahir, alamat, telepon, tahunMasuk, tahunLulus, job, jabatan);
        userRepository.close();
    }
}
