package com.example.personinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtInputName, edtCMND, edtInformation;
    RadioGroup radioGroup;
    CheckBox chkReadBook, chkReadNews, chkReadCode;
    Button btnSendInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
        btnSendInformation.setOnClickListener(this);
    }

    private void setWidget() {
        edtInputName = findViewById( R.id.edtInputName);
        edtCMND = findViewById( R.id.edtCMND);
        edtInformation = findViewById( R.id.edtInformation);
        radioGroup = findViewById( R.id.radioGroup);
        chkReadBook = findViewById( R.id.chkReadBook);
        chkReadNews = findViewById( R.id.chkReadNews);
        chkReadCode = findViewById( R.id.chkReadCode);
        btnSendInformation = findViewById( R.id.btnSendInformation);
    }

    @Override
    public void onClick(View v) {
        String msg = "";
        try {
            String name = edtInputName.getText().toString().trim();
            if (name.length()<3) {
                edtInputName.requestFocus();
                edtInputName.selectAll();
                Toast.makeText(this, "Tên phải lớn hơn 3 ký tự", Toast.LENGTH_LONG).show();
                return;
            }

            String cmnd = edtCMND.getText().toString().trim();
            if (cmnd.length()!=9) {
                edtCMND.requestFocus();
                edtCMND.selectAll();
                Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
                return;
            }

            int id = radioGroup.getCheckedRadioButtonId();
            if (id==-1){
                Toast.makeText(this, "Phải chọn bằng cấp",Toast.LENGTH_LONG).show();
                return;
            }
            RadioButton rad = findViewById(id);
            String science = rad.getText().toString().trim();

            String interest = "";
            if (chkReadBook.isChecked())
                interest += chkReadBook.getText() +"\n";
            if (chkReadCode.isChecked())
                interest += chkReadCode.getText() +"\n";
            if (chkReadNews.isChecked())
                interest += chkReadNews.getText() +"\n";
            String information = edtInformation.getText().toString();

            msg = name + "\n";
            msg += cmnd +"\n";
            msg += science + "\n";
            msg += interest + "\n";
            msg += "-------------------------------\n";
            msg += "Thông tin bổ sung\n";
            msg += information+"\n";
            msg+="-------------------------------\n";
        } catch (Exception e) {
            Toast.makeText(this, "Không để trống",Toast.LENGTH_LONG).show();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", (dialog, which) -> dialog.cancel());
        builder.setMessage(msg);
        builder.create().show();
    }
}