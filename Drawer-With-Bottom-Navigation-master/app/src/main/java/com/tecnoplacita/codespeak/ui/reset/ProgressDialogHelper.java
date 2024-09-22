package com.tecnoplacita.codespeak.ui.reset;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.tecnoplacita.codespeak.R;

public class ProgressDialogHelper {

    private AlertDialog progressDialog;

    public ProgressDialogHelper(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View progressView = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        builder.setView(progressView);
        builder.setCancelable(false); // Para que no se pueda cancelar el diálogo tocando fuera de él
        progressDialog = builder.create();
    }

    public void show() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void hide() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
