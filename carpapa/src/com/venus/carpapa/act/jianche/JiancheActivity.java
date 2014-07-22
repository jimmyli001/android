package com.venus.carpapa.act.jianche;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.venus.carpapa.R;
import com.venus.carpapa.util.HttpUtil;
import com.venus.carpapa.vo.ChildTargeList;
import com.venus.carpapa.vo.ChildTargeList_thread;
import com.venus.carpapa.vo.ChildTargetList_secend;
import com.venus.carpapa.vo.ChildTrgeList_four;

public class JiancheActivity extends FragmentActivity {

	JiancheDetailFrame mfrag_detail;
	ListView mListView, mbListView;
	String carSellCoding;
	mbAdapter mmAdapter;
	ArrayList<ChildTargeList> str;
	ArrayList<ChildTargeList_thread> str_thread;
	ArrayList<ChildTrgeList_four> str_four;
	private int name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.act_jianche);
		mListView = (ListView) findViewById(R.id.listview);

		mbListView = (ListView) findViewById(R.id.frag_detail);

		carSellCoding = getIntent().getExtras().getString("carSellCoding");
		name = getIntent().getExtras().getInt("name");

		LoadData();
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (null != str) {
					mmAdapter.clear();
					mmAdapter.addAll(str_thread.get((int) arg3)
							.getmChildTrgeList_fours());
					mbListView.setAdapter(mmAdapter);
				}
			}

		});
	}

	private void LoadData() {
		new AsyncTask<Object, Object, ArrayList<ChildTargeList>>() {
			@Override
			protected ArrayList<ChildTargeList> doInBackground(Object... params) {
				str = HttpUtil.getChildTargeList4JSON(carSellCoding);
				return str;
			}
			protected void onPostExecute(ArrayList<ChildTargeList> result) {
				if (null != str) {
					str_thread = new ArrayList<ChildTargeList_thread>();
					for (int i = 0; i < str.get(0)
							.getmChildTargetList_secends().size(); i++) {
						str_thread.addAll(str.get(0)
								.getmChildTargetList_secends().get(i)
								.getmChildTargeList_threads());
					}
					myAdapter mAdapter = new myAdapter(JiancheActivity.this,
							str_thread);
					mmAdapter = new mbAdapter(JiancheActivity.this);
					mmAdapter.addAll(str_thread.get(0)
							.getmChildTrgeList_fours());
					mListView.setAdapter(mAdapter);
					mbListView.setAdapter(mmAdapter);

				}
			}

		}.execute();

	}

	public void back(View v) {
		this.finish();
	}

	class myAdapter extends BaseAdapter {

		Context context;
		ArrayList<ChildTargeList_thread> mChildTargetList_secend;

		public myAdapter(Context context,
				ArrayList<ChildTargeList_thread> mChildTargetList_secend) {
			this.context = context;
			this.mChildTargetList_secend = mChildTargetList_secend;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final Holder holder;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(
						R.layout.adapter_ceshi_left, parent, false);
				holder = new Holder();
				holder.txl = (TextView) convertView
						.findViewById(R.id.hexingText);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.txl.setText(mChildTargetList_secend.get(position)
					.getTargetName());
			return convertView;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mChildTargetList_secend.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mChildTargetList_secend.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}

	class Holder {
		TextView txl;
	}

	class mbAdapter extends ArrayAdapter<ChildTrgeList_four> {

		public mbAdapter(Context context) {
			super(context, 0);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final mHolder holder;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(
						R.layout.item_jianche, parent, false);
				holder = new mHolder();
				holder.tx = (TextView) convertView
						.findViewById(R.id.itemNameTextView);
				holder.sp = (Spinner) convertView
						.findViewById(R.id.statusTextView);

				convertView.setTag(holder);
			} else {
				holder = (mHolder) convertView.getTag();
			}
			holder.tx.setText(getItem(position).getTargetName());

			String[] a = new String[getItem(position)
					.getmChildTargetList_fives().size()];
			for (int i = 0; i < a.length; i++) {
				a[i] = getItem(position).getmChildTargetList_fives().get(i)
						.getTargetName();
			}
			setspinner(holder.sp, a);
			return convertView;
		}
	}

	private void setspinner(Spinner s, String[] mItems) {
		// 建立Adapter并且绑定数据源
		ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(
				JiancheActivity.this, android.R.layout.simple_spinner_item,
				mItems);
		// 绑定 Adapter到控件
		s.setAdapter(_Adapter);

	}

	class mHolder {
		TextView tx;
		Spinner sp;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// DialogUtil.toast(this, "JiancheActivity onActivityResult");
		mfrag_detail.onar(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	// 左前椅子板
	// 发动机常盖
	// 左前椅子板
	// 左前门
	// 右前门
	// 车顶
	// 左后门
	// 右后门

	// 左后翼子板
	// 右后翼子板
	// 行李箱盖
	// 后保险缸

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			this.finish();
		}
		return super.dispatchKeyEvent(event);
	}
}
