package com.aricneto.twistytimer.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.aricneto.twistify.R;
import com.aricneto.twistytimer.adapter.BottomSheetSpinnerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Implements a layout for easy creation of spinner-like bottom sheet dialogs (like the ones seen in
 * the puzzle selection screen).
 */

public class BottomSheetSpinnerDialog extends BottomSheetDialogFragment {

    @BindView(R.id.title) TextView titleTextView;
    @BindView(R.id.list) ListView listView;

    private Context mContext;

    private BottomSheetSpinnerAdapter mAdapter;
    private AdapterView.OnItemClickListener mClickListener;

    private String titleText;
    private int titleIcon;
    private Unbinder mUnbinder;

    public static BottomSheetSpinnerDialog newInstance() {
        return new BottomSheetSpinnerDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_puzzle_spinner, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mContext = getContext();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(mClickListener);

        titleTextView.setText(titleText);
        titleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, titleIcon != 0 ? ContextCompat.getDrawable(mContext, titleIcon) : null, null);
    }

    public void setTitle(String title, @DrawableRes int iconRes) {
        titleText = title;
        titleIcon = iconRes;
    }

    public void setListAdapter(BottomSheetSpinnerAdapter adapter) {
        mAdapter = adapter;
    }

    public void setListClickListener(AdapterView.OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}