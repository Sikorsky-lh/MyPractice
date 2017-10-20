package com.example.lihang.mypractice.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lihang.mypractice.MyFile;
import com.example.lihang.mypractice.R;
import com.example.lihang.mypractice.adapter.FileAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileActivity extends AppCompatActivity {

	OutputStream os;
	BufferedWriter writer = null;

	InputStream is;
	BufferedReader reader = null;

	private EditText et;
	private RecyclerView recyclerView;

	private File currentParent;
	private File[] currentFiles;
	private List<MyFile> fileList = new ArrayList<>();

	private FileAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);

		init();
		save();

		findViewById(R.id.restore).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				String data = restore();
//				et.setText(data);
//				et.setSelection(data.length());
				if (currentParent.getPath().equals(getFilesDir().getPath())) {
					Toast.makeText(FileActivity.this, "已达到最顶层文件夹", Toast.LENGTH_SHORT).show();
					return;
				}
				currentParent = currentParent.getParentFile();
				currentFiles = currentParent.listFiles();
				inflateFileList(currentFiles);
				et.setText(currentParent.getPath());
			}
		});

//		currentParent = Environment.getExternalStorageDirectory();
//		currentFiles = currentParent.listFiles();

		currentParent = getFilesDir();
		currentFiles = getFilesDir().listFiles();
		inflateFileList(currentFiles);
	}

	private void init() {
		et = (EditText) findViewById(R.id.et_data);
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		adapter = new FileAdapter(fileList);

		adapter.setCallBack(new FileAdapter.CallBack() {
			@Override
			public void onItemClick(int pos) {
				if (currentFiles[pos].isFile()) {
					Toast.makeText(FileActivity.this, "这是一个文件，无法进入", Toast.LENGTH_SHORT).show();
					return;
				}

				File[] temp = currentFiles[pos].listFiles();
				if (temp == null || temp.length == 0) {
					Toast.makeText(FileActivity.this, "当前路径不可访问，或该路径下没有文件", Toast.LENGTH_SHORT).show();
				} else {
					currentParent = currentFiles[pos];
					currentFiles = temp;
					inflateFileList(currentFiles);
					et.setText(currentParent.getPath());
				}
			}
		});

		recyclerView.setAdapter(adapter);
		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(manager);
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

	private void inflateFileList(File[] files) {
		fileList.clear();
		for (int i = 0; i < files.length; i++) {
			fileList.add(new MyFile(files[i].getName(), files[i].isDirectory()));
		}
		adapter.notifyDataSetChanged();
	}

}
