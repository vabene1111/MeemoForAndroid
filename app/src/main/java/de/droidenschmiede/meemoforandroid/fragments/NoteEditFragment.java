package de.droidenschmiede.meemoforandroid.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.droidenschmiede.meemoforandroid.R;
import de.droidenschmiede.meemoforandroid.activity.NoteActivity;

/**
 * Created by vabene1111 on 04.07.2017.
 * Fragment to Edit Notes
 */

public class NoteEditFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public NoteEditFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NoteEditFragment newInstance(int sectionNumber) {
        NoteEditFragment fragment = new NoteEditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);


        return rootView;
    }
}
