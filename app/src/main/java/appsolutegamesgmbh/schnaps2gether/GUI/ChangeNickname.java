package appsolutegamesgmbh.schnaps2gether.GUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import appsolutegamesgmbh.schnaps2gether.R;

/**
 * Created by kirederf on 17.04.15.
 */
public class ChangeNickname extends DialogFragment {

    private EditText editNickname;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_nickname, null);
        editNickname = (EditText) view.findViewById(R.id.nickname);
        SharedPreferences settings = getActivity().getPreferences(getActivity().MODE_PRIVATE);
        editNickname.setText(settings.getString("nickname", "Nickname"));

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences settings = getActivity().getPreferences(getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("nickname", editNickname.getText().toString());
                // Commit the edits!
                editor.commit();
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
