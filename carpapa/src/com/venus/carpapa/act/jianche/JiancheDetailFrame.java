package com.venus.carpapa.act.jianche;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.venus.carpapa.R;
import com.venus.carpapa.util.BMapUtil;

@SuppressLint({ "NewApi", "ResourceAsColor" })
public class JiancheDetailFrame extends Fragment {

	Activity act = null;
	LinearLayout qdLine;
	BMapUtil mapUtil;
	String photoPath;
	String path = Environment.getExternalStorageDirectory().getAbsolutePath()
			+ "/Download/";// 临时目录，为了在手机上好找
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 在这里初始化fragment的页面
	
		return inflater.inflate(R.layout.jianche_detail, container, false);
	}

	public void onar(int requestCode, int resultCode, Intent data) {
		// DialogUtil.toast(act, "---onActivityResult");
		// 拍照
		if (requestCode == PHOTOHRAPH) {
			mapUtil.startPhotoZoom(Uri.fromFile(new File(photoPath)));
		}

		// 读取相册缩放图片
		if (requestCode == PHOTOZOOM) {
			mapUtil.startPhotoZoom(data.getData());
		}
		// 处理结果
		if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -
																		// 100)压缩文件
																		// exceptionTextView.setImageBitmap(photo);
				mapUtil.writeToFile(photoPath, photo, 85);
				// isHeadChanged = true;
			}

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// 以下是拍照相关
		super.onActivityResult(requestCode, resultCode, data);

	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		act = this.getActivity();
		mapUtil = new BMapUtil(act);
	}

}
