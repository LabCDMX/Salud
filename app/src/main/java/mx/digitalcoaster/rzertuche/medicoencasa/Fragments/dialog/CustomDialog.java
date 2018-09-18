package mx.digitalcoaster.rzertuche.medicoencasa.Fragments.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.digitalcoaster.rzertuche.medicoencasa.R;

public class CustomDialog extends DialogFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.progress_dialog_fragment,container,false);
        return mView;
    }
}
