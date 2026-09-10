package br.com.techz.jardimviver;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import br.com.techz.jardimviver.data.DatabaseHelper;
import br.com.techz.jardimviver.model.ResponsavelDTO;
import br.com.techz.jardimviver.ui.ResponsavelAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ResponsavelAdapter.Listener {
    private TextInputEditText editNome;
    private TextInputEditText editSobrenome;
    private TextInputEditText editEmail;
    private TextInputEditText editIdade;
    private TextInputEditText editEndereco;
    private MaterialButton buttonSalvar;
    private MaterialButton buttonCancelar;
    private TextView textListaVazia;

    private DatabaseHelper databaseHelper;
    private ResponsavelAdapter adapter;
    private Long idEmEdicao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos campos da tela solicitada no enunciado.
        editNome = findViewById(R.id.editNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editEmail = findViewById(R.id.editEmail);
        editIdade = findViewById(R.id.editIdade);
        editEndereco = findViewById(R.id.editEndereco);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonCancelar = findViewById(R.id.buttonCancelar);
        textListaVazia = findViewById(R.id.textListaVazia);

        databaseHelper = new DatabaseHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerResponsaveis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResponsavelAdapter(this);
        recyclerView.setAdapter(adapter);

        buttonSalvar.setOnClickListener(v -> salvarOuAtualizar());
        buttonCancelar.setOnClickListener(v -> limparFormulario());

        carregarResponsaveis();
    }

    private void salvarOuAtualizar() {
        String nome = texto(editNome);
        String sobrenome = texto(editSobrenome);
        String email = texto(editEmail);
        String idadeTexto = texto(editIdade);
        String endereco = texto(editEndereco);

        if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty()
                || idadeTexto.isEmpty() || endereco.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Informe um e-mail válido.");
            editEmail.requestFocus();
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException e) {
            editIdade.setError("Idade inválida.");
            return;
        }

        if (idade < 18 || idade > 120) {
            editIdade.setError("Informe uma idade entre 18 e 120 anos.");
            return;
        }

        ResponsavelDTO responsavel = new ResponsavelDTO(
                idEmEdicao == null ? 0 : idEmEdicao,
                nome,
                sobrenome,
                email,
                idade,
                endereco
        );

        if (idEmEdicao == null) {
            long id = databaseHelper.inserir(responsavel);
            if (id > 0) {
                Toast.makeText(this, "Cadastro salvo com sucesso.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Não foi possível salvar o cadastro.", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            int linhas = databaseHelper.atualizar(responsavel);
            if (linhas > 0) {
                Toast.makeText(this, "Cadastro atualizado.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Não foi possível atualizar o cadastro.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        limparFormulario();
        carregarResponsaveis();
    }

    private void carregarResponsaveis() {
        List<ResponsavelDTO> responsaveis = databaseHelper.listarTodos();
        adapter.atualizarLista(responsaveis);
        textListaVazia.setVisibility(responsaveis.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onEditar(ResponsavelDTO responsavel) {
        idEmEdicao = responsavel.getId();
        editNome.setText(responsavel.getNome());
        editSobrenome.setText(responsavel.getSobrenome());
        editEmail.setText(responsavel.getEmail());
        editIdade.setText(String.valueOf(responsavel.getIdade()));
        editEndereco.setText(responsavel.getEndereco());
        buttonSalvar.setText(R.string.atualizar);
        buttonCancelar.setVisibility(View.VISIBLE);
        editNome.requestFocus();
    }

    @Override
    public void onExcluir(ResponsavelDTO responsavel) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir cadastro")
                .setMessage("Deseja excluir o cadastro de " + responsavel.getNome() + " " + responsavel.getSobrenome() + "?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Excluir", (dialog, which) -> {
                    databaseHelper.excluir(responsavel.getId());
                    if (idEmEdicao != null && idEmEdicao == responsavel.getId()) {
                        limparFormulario();
                    }
                    carregarResponsaveis();
                    Toast.makeText(this, "Cadastro excluído.", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void limparFormulario() {
        idEmEdicao = null;
        editNome.setText("");
        editSobrenome.setText("");
        editEmail.setText("");
        editIdade.setText("");
        editEndereco.setText("");
        editNome.setError(null);
        editEmail.setError(null);
        editIdade.setError(null);
        buttonSalvar.setText(R.string.salvar);
        buttonCancelar.setVisibility(View.GONE);
    }

    private String texto(TextInputEditText campo) {
        return campo.getText() == null ? "" : campo.getText().toString().trim();
    }
}
