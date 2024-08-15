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

import com.example.projectakhirvsga.model.User;
import com.example.projectakhirvsga.repository.UserRepository;

public class EditDataAct extends AppCompatActivity {
    private EditText editName, editAddress, editJabatan, editNim, editTempatLahir, editTglLahir, editTlp, editJob, editMasuk, editLulus;
    private ImageButton btnBack;
    private Button btnSubmit, btnDelete;
    private UserRepository userRepository;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userRepository = new UserRepository(this);
        userId = getIntent().getLongExtra("USER_ID", -1);
        if (userId == -1) {
            Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        initalizeViews();
        initializeOnClickListeners();
        loadUserData();
    }
    private void initalizeViews() {
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
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void initializeOnClickListeners() {
        btnSubmit.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String domisili = editAddress.getText().toString().trim();
            String nim = editNim.getText().toString().trim();
            String tmptlahir = editTempatLahir.getText().toString().trim();
            String tgllahir = editTglLahir.getText().toString().trim();
            String thnmasuk = editMasuk.getText().toString().trim();
            String thnlulus = editLulus.getText().toString().trim();
            String job = editJob.getText().toString().trim();
            String jbtn = editJabatan.getText().toString().trim();
            String tlp = editTlp.getText().toString().trim();

            if (name.isEmpty() || domisili.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                updateDataToSQLite(userId, name, domisili, nim, tmptlahir, tgllahir, thnmasuk, thnmasuk, thnlulus, job, jbtn, tlp);
                Toast.makeText(this, "User data updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnBack.setOnClickListener(v -> {
            finish();
        } );

        btnDelete.setOnClickListener(v -> deleteUser());
    }

    private void deleteUser() {
        userRepository.open();
        userRepository.deleteUser(userId);
        userRepository.close();
        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void loadUserData() {
        userRepository.open();
        User user = userRepository.getUserById(userId);
        userRepository.close();

        if (user != null) {
            editName.setText(user.getName());
            editAddress.setText(user.getDomisili());
            editNim.setText(user.getNim());
            editMasuk.setText(user.getMsk());
            editLulus.setText(user.getLls());
            editTempatLahir.setText(user.getTmpt());
            editTglLahir.setText(user.getTgl());
            editJob.setText(user.getJob());
            editJabatan.setText(user.getJbtn());
            editTlp.setText(user.getTlp());
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void updateDataToSQLite(long id, String name, String domisili, String nim, String tmptlahir, String tgllahir, String thnmasuk, String  thnlulus, String  job, String jbtn, String tlp, String s) {
        userRepository.open();
        userRepository.updateUser(id, name, domisili, nim, tmptlahir, tgllahir, thnmasuk, thnmasuk, thnlulus, job, jbtn, tlp);
        userRepository.close();
    }
}