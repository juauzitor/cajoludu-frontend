package br.cajoludu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    login(email, password);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login bem-sucedido
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("TAG", "Login bem-sucedido: " + user.getEmail());
                            Toast.makeText(MainActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();

                            // Redirecionar para a ListBibliotecaActivity e passar o usuário logado
                            Intent intent = new Intent(MainActivity.this, ListaBibliotecaActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish(); // Finalizar LoginActivity para impedir o retorno usando o botão Voltar
                        } else {
                            // Falha ao fazer login
                            Log.w("TAG", "Falha ao fazer login", task.getException());
                            Toast.makeText(MainActivity.this, "Falha ao fazer login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
