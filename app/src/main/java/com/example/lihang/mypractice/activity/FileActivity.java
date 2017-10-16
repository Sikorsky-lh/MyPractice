package com.example.lihang.mypractice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lihang.mypractice.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileActivity extends AppCompatActivity {

	OutputStream os;
	BufferedWriter writer = null;

	InputStream is;
	BufferedReader reader = null;

	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);

		et = (EditText) findViewById(R.id.et_data);
		save();

		findViewById(R.id.restore).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String data = restore();
				et.setText(data);
				et.setSelection(data.length());
			}
		});
	}

	private void save() {
		try {
			os = openFileOutput("data", MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write("data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String restore() {
		try {
			is = openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder builder = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
