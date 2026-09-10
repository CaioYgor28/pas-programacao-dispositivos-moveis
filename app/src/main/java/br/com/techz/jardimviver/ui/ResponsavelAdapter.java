package br.com.techz.jardimviver.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import br.com.techz.jardimviver.R;
import br.com.techz.jardimviver.model.ResponsavelDTO;

import java.util.ArrayList;
import java.util.List;

public class ResponsavelAdapter extends RecyclerView.Adapter<ResponsavelAdapter.ViewHolder> {
    public interface Listener {
        void onEditar(ResponsavelDTO responsavel);
        void onExcluir(ResponsavelDTO responsavel);
    }

    private final List<ResponsavelDTO> itens = new ArrayList<>();
    private final Listener listener;

    public ResponsavelAdapter(Listener listener) {
        this.listener = listener;
    }

    public void atualizarLista(List<ResponsavelDTO> novosItens) {
        itens.clear();
        itens.addAll(novosItens);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_responsavel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResponsavelDTO item = itens.get(position);
        holder.textNomeCompleto.setText(item.getNome() + " " + item.getSobrenome());
        holder.textEmail.setText(item.getEmail());
        holder.textDetalhes.setText(item.getIdade() + " anos • " + item.getEndereco());
        holder.buttonEditar.setOnClickListener(v -> listener.onEditar(item));
        holder.buttonExcluir.setOnClickListener(v -> listener.onExcluir(item));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textNomeCompleto;
        final TextView textEmail;
        final TextView textDetalhes;
        final MaterialButton buttonEditar;
        final MaterialButton buttonExcluir;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNomeCompleto = itemView.findViewById(R.id.textNomeCompleto);
            textEmail = itemView.findViewById(R.id.textEmail);
            textDetalhes = itemView.findViewById(R.id.textDetalhes);
            buttonEditar = itemView.findViewById(R.id.buttonEditar);
            buttonExcluir = itemView.findViewById(R.id.buttonExcluir);
        }
    }
}
