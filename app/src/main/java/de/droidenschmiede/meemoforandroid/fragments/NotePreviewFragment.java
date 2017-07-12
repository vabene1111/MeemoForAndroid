package de.droidenschmiede.meemoforandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.tiagohm.markdownview.MarkdownView;
import de.droidenschmiede.meemoforandroid.R;
import de.droidenschmiede.meemoforandroid.helper.Singleton;
import de.droidenschmiede.meemoforandroid.objects.Thing;


/**
 * Created by vabene1111 on 04.07.2017.
 * Note Preview Fragment
 */

public class NotePreviewFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public NotePreviewFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NotePreviewFragment newInstance(int sectionNumber) {
        NotePreviewFragment fragment = new NotePreviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note_preview, container, false);

        MarkdownView mv = (MarkdownView) rootView.findViewById(R.id.mv_F_notePreview_main);
        Singleton singleton = Singleton.getInstance();

        Thing thing = singleton.getActiveThing();

        mv.loadMarkdown(thing.getContent());

        Date modDate = new java.util.Date(Long.parseLong(thing.getModifiedAt()));
        Format formatter = new SimpleDateFormat("dd. MMM - yy HH:mm", Locale.GERMAN);

        TextView tv_info = (TextView) rootView.findViewById(R.id.tv_F_notePreview_info);
        tv_info.setText(formatter.format(modDate));

        return rootView;
    }
}
