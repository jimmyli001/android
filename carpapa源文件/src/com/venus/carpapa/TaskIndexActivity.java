package com.venus.carpapa;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.core.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.venus.carpapa.adapter.ChejianAdapter;
import com.venus.carpapa.resp.CarOrderResp;
import com.venus.carpapa.util.HttpUtil;
import com.venus.carpapa.util.Logger;
import com.venus.carpapa.util.ReqJsonUtil;
import com.venus.carpapa.vo.CarSellDtoVo;
import com.venus.carpapa.vo.CarSellDtoVoList;
import com.venus.carpapa.vo.ChejianVo;

public class TaskIndexActivity extends BaseActivity {
	@ViewInject(id = R.id.chejianListView)
	ListView chejianListView;
	@ViewInject(id = R.id.exitBtn, click = "exit")
	Button exitBtn;
	@ViewInject(id = R.id.countTextView)
	TextView countTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_index);
		data_test();
	}

	public void data_test() {
		new AsyncTask<Object, Object, CarSellDtoVoList>() {

			@Override
			protected CarSellDtoVoList doInBackground(Object... arg0) {

				return HttpUtil.CarOrder4JSON(1);
			}

			protected void onPostExecute(CarSellDtoVoList result) {
				if (null != result) {

					ChejianAdapter apapter = new ChejianAdapter(
							TaskIndexActivity.this,
							result.getCarSellDtoVo_List());
					chejianListView.setAdapter(apapter);
					countTextView.setText("共" + result.getSum() + "条未完成");
				}

			};

		}.execute();
	}

	public void exit(View v) {
		finish();
	}
}
