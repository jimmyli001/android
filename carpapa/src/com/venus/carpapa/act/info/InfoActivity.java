package com.venus.carpapa.act.info;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.venus.carpapa.R;

public class InfoActivity extends FragmentActivity {

	private InfoDetailFrame mFragment;

	private RadioButton peizhi;
	private RadioButton beizhu;
	private RadioButton shouxu;
	private int CarId;

	private RelativeLayout prossloading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_task_info);
		peizhi = (RadioButton) findViewById(R.id.peizhiText);
		beizhu = (RadioButton) findViewById(R.id.descText);
		shouxu = (RadioButton) findViewById(R.id.shouxuText);
		prossloading = (RelativeLayout) findViewById(R.id.loading_progress);
		CarId = getIntent().getExtras().getInt("carId");
		mFragment = InfoDetailFrame.newsInstance(CarId);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.frag_detail, mFragment);
		ft.addToBackStack(null);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
		prossloading.setVisibility(View.GONE);
		peizhi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != mFragment)
					mFragment.setPeizhi();
			}
		});
		beizhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (null != mFragment)
					mFragment.setBeizhu();
			}
		});
		shouxu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (null != mFragment)
					mFragment.setShouxu();
			}
		});

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			this.finish();
		}
		return super.dispatchKeyEvent(event);
	}

	public void back(View v) {
		this.finish();
	}

	public void date(View v) {
		this.finish();
	}
}
