package br.com.etecia.meus_direitos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Area_Atuacao> mCartao;

    public CustomAdapter(Context mContext, ArrayList<Area_Atuacao> mCartao) {
        this.mContext = mContext;
        this.mCartao = mCartao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modelo_area_atuacao, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.area_atuacao.setText(mCartao.get(position).getNome());

        //intent.putExtra("Nome", mCartao.get(position).getNome());
        //intent.putExtra("Imagem", mCartao.get(position).getImagem());
    }

    @Override
    public int getItemCount() {
        return mCartao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView area_atuacao;
        ImageView imagemCartao;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            area_atuacao = itemView.findViewById(R.id.nome_area_atuacao);
            imagemCartao = itemView.findViewById(R.id.imagem_cartao_area_atuaracao);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
