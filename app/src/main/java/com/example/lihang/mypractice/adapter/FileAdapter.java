package com.example.lihang.mypractice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lihang.mypractice.MyFile;
import com.example.lihang.mypractice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihang on 2017/10/17.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

	private List<MyFile> fileList = new ArrayList<>();
	private CallBack callBack;

	public FileAdapter(List<MyFile> fileList) {
		this.fileList = fileList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		MyFile myFile = fileList.get(position);
		holder.textView.setText(myFile.getName());
		if (myFile.isFolder()) {
			holder.imageView.setImageResource(R.drawable.folder);
		} else {
			holder.imageView.setImageResource(R.drawable.file);
		}
		holder.view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callBack.onItemClick(position);
			}
		});
	}

	@Override
	public int getItemCount() {
		return fileList.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		TextView textView;
		ImageView imageView;
		View view;

		public ViewHolder(View itemView) {
			super(itemView);
			view = itemView;
			textView = itemView.findViewById(R.id.file_name);
			imageView = itemView.findViewById(R.id.file_img);
		}
	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	public interface CallBack {
		void onItemClick(int pos);
	}
}
