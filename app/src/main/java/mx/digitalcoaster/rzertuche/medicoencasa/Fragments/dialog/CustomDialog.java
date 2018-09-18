package mx.digitalcoaster.rzertuche.medicoencasa.Fragments.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import mx.digitalcoaster.rzertuche.medicoencasa.R;

public class CustomDialog extends android.support.v4.app.DialogFragment{

    TextView tv_dialog;
    ProgressBar pb_dialog;
    View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = getActivity().getLayoutInflater().inflate(R.layout.progress_dialog_fragment,container,false);

        tv_dialog = mView.findViewById(R.id.tv_dialog);
        pb_dialog = mView.findViewById(R.id.pb_dialog);

        return mView;
    }

    public void textDataLogind(String textDialog){
        if (mView != null) tv_dialog.setText(textDialog);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
