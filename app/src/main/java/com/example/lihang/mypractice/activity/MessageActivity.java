package com.example.lihang.mypractice.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lihang.mypractice.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MessageActivity extends AppCompatActivity {

	private EditText content, number;
	private Button send, select;

	private SmsManager smsManager;

	private String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS};

	BaseAdapter adapter;
	private List<String> sendList = new ArrayList<>();
	private Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		content = (EditText) findViewById(R.id.content);
		number = (EditText) findViewById(R.id.number);

		send = (Button) findViewById(R.id.send);
		select = (Button) findViewById(R.id.select);

		runTimePermission();
		init();

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String phone = number.getText().toString();
				String msg = content.getText().toString();

				if (TextUtils.isEmpty(phone)) {
					showToast("电话号码不能为空");
					return;
				}

				if (TextUtils.isEmpty(msg)) {
					showToast("内容不能为空");
					return;
				}

				if(sendList.size()==0){
					PendingIntent pi = PendingIntent.getActivity(MessageActivity.this, 0, new Intent(), 0);
					smsManager.sendTextMessage(number.getText().toString(), null, content.getText().toString(), pi, null);
					showToast("发送成功");
				}

				for (String num : sendList) {
					PendingIntent pi = PendingIntent.getActivity(MessageActivity.this, 0, new Intent(), 0);
					smsManager.sendTextMessage(num, null, content.getText().toString(), pi, null);
					showToast("发送成功");
				}
			}
		});

		select.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				View selectView = getLayoutInflater().inflate(R.layout.contact_view, null);
				final ListView listView = selectView.findViewById(R.id.contact_list);



				listView.setAdapter(adapter);
				new AlertDialog.Builder(MessageActivity.this)
						.setView(selectView)
						.setPositiveButton("确认", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int pos) {
								sendList.clear();
								for (int i = 0; i < 15; i++) {
									CheckBox checkBox = (CheckBox) listView.getChildAt(i);
									if (checkBox.isChecked()) {
										sendList.add(checkBox.getText().toString());
									}
								}
								number.setText(sendList.toString());
								number.setSelection(number.getText().toString().length());
							}
						})
						.show();
			}
		});
	}

	private void init() {
		smsManager = SmsManager.getDefault();
		adapter = new BaseAdapter() {

			Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

			@Override
			public int getCount() {
				return cursor.getCount();
			}

			@Override
			public Object getItem(int i) {
				return i;
			}

			@Override
			public long getItemId(int i) {
				return i;
			}

			@Override
			public View getView(int i, View view, ViewGroup viewGroup) {
				cursor.moveToPosition(i);
				CheckBox checkBox = new CheckBox(mContext);
				String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
						.replace("-", "")
						.replace(" ", "");
				checkBox.setText(number);
				if (isChecked(number)) {
					checkBox.setChecked(true);
				}
				return checkBox;
			}
		};
	}

	private void showToast(String msg) {
		Toast.makeText(MessageActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	private void runTimePermission() {

		if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(MessageActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);

		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
					showToast("我们需要获取发短信的权限");
				}
				break;

			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}

	}

	private boolean isChecked(String number) {
		for (String s : sendList) {
			if (s.equals(number)) {
				return true;
			}
		}
		return false;
	}
}
