package com.venus.carpapa;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.venus.carpapa.act.info.InfoActivity;
import com.venus.carpapa.act.jianche.JiancheActivity;
import com.venus.carpapa.act.task.UpPhotoActivity;

public class MainActivity extends BaseActivity {
	@ViewInject(id = R.id.photoTv, click = "photo")
	TextView photoTv;
	@ViewInject(id = R.id.noTextView)
	TextView noTextView;
	@ViewInject(id = R.id.infoText, click = "info")
	TextView infoText;
	@ViewInject(id = R.id.jiancheText, click = "jianche")
	TextView jiancheText;

	@ViewInject(id = R.id.shiguText, click = "shigu")
	TextView shiguText;
	private int CarId;
	private String carSellCoding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// data();
		String no = this.getIntent().getStringExtra("no");
		CarId = this.getIntent().getExtras().getInt("carId");
		carSellCoding = this.getIntent().getExtras().getString("carSellCoding");

		noTextView.setText(no);
	}

	public void back(View v) {
		this.finish();
	}

	// ShiguActivity
	public void shigu(View v) {

		Intent i = new Intent();
		i.putExtra("carSellCoding", carSellCoding);
		i.putExtra("name", 1);
		i.setClass(this, JiancheActivity.class);
		startActivity(i);
	}

	public void info(View v) {
		Intent i = new Intent();
		i.putExtra("carId", CarId);
		i.setClass(this, InfoActivity.class);
		startActivity(i);
	}

	public void jianche(View v) {
		Intent i = new Intent();
		i.putExtra("carSellCoding", carSellCoding);
		i.putExtra("name", 0);
		i.setClass(this, JiancheActivity.class);
		startActivity(i);

	}

	public void photo(View v) {
		// DialogUtil.toast(this, "photo");
		startActivity(new Intent(this, UpPhotoActivity.class));
	}
}
