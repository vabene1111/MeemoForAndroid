package de.droidenschmiede.meemoforandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.tiagohm.markdownview.MarkdownView;
import de.droidenschmiede.meemoforandroid.R;
import de.droidenschmiede.meemoforandroid.objects.Thing;

/**
 * Created by vabene1111 on 04.07.2017.
 * Used to populate Note List
 *
 */

public class NoteItemAdapter extends ArrayAdapter<Thing> {
    public NoteItemAdapter(@NonNull Context context, ArrayList<Thing> things) {
        super(context, 0,things);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Thing thing = getItem(position);

        NoteViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
            holder = new NoteViewHolder();
            holder.mv_note = (TextView) convertView.findViewById(R.id.mv_nItem_note);
            holder.tv_info = (TextView) convertView.findViewById(R.id.tv_nItem_info);
            convertView.setTag(holder);
        }else{
            holder = (NoteViewHolder) convertView.getTag();
        }


        //holder.mv_note.loadMarkdown(thing.getContent()); TODO markdown rendering in background thread
        holder.mv_note.setText(thing.getRichContent());

        Date modDate = new java.util.Date(Long.parseLong(thing.getModifiedAt()));
        Format formatter = new SimpleDateFormat("dd. MMM - yy HH:mm", Locale.GERMAN); //TODO user locale
        holder.tv_info.setText(formatter.format(modDate));

        return convertView;
    }

    static class NoteViewHolder {
        TextView mv_note;
        TextView tv_info;
    }
}
