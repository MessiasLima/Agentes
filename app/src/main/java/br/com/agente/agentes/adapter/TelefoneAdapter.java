package br.com.agente.agentes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Telefone;
import br.com.agente.agentes.util.Fonte;

/**
 * Created by messias on 3/10/16.
 * @author Messias Lima
 */
public class TelefoneAdapter extends ArrayAdapter<Telefone> {
    public TelefoneAdapter(Context context, int resource, List<Telefone> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.telefone_list_item, null);
        }
        Telefone t = getItem(position);

        if (t != null) {

            TextView nome = (TextView) convertView.findViewById(R.id.nome_telefones_list_item);
            nome.setText(t.getNome());
            Fonte.setarFonteTitulo(nome);

            TextView telefone = (TextView) convertView.findViewById(R.id.telefone_telefones_list_item);
            telefone.setText(t.getTelefone());

        }
        return convertView;


    }
}
