package com.example.calcmedia;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail, edtNome, edtDiciplina, edtIdade, edtNota01, edtNota02;
    private Button btCalc, btLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando as views
        edtEmail = findViewById(R.id.EdtEmail);
        edtNome = findViewById(R.id.EdtNome);
        edtDiciplina = findViewById(R.id.edtDiciplina);
        edtNota01 = findViewById(R.id.edtNota01);
        edtNota02 = findViewById(R.id.edtNota02);
        btCalc = findViewById(R.id.btCalc);
        btLimpar = findViewById(R.id.btLimpar);

        // Configura o listener para o botão Calcular
        btCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowMessage();
            }
        });

        // Configura o listener para o botão Limpar
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    // Método para calcular a média e exibir a mensagem concatenada
    private void calculateAndShowMessage() {
        String email = edtEmail.getText().toString();
        String nome = edtNome.getText().toString();
        String diciplina = edtDiciplina.getText().toString();
        String nota1Str = edtNota01.getText().toString();
        String nota2Str = edtNota02.getText().toString();

        // Validações
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Por favor, insira um email válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(diciplina) || TextUtils.isEmpty(nota1Str) || TextUtils.isEmpty(nota2Str)) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        double nota1 = Double.parseDouble(nota1Str);
        double nota2 = Double.parseDouble(nota2Str);
        double media = (nota1 + nota2) / 2;

        // Exibe a mensagem com nome, disciplina e média
        String mensagem = "Nome: " + nome + "\nDisciplina: " + diciplina + "\nMédia: " + media;
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    // Método para limpar os campos
    private void clearFields() {
        edtEmail.setText("");
        edtNome.setText("");
        edtDiciplina.setText("");
        edtNota01.setText("");
        edtNota02.setText("");
        Toast.makeText(this, "Campos limpos.", Toast.LENGTH_SHORT).show();
    }

    // Método para validar o email
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}