package com.venus.carpapa.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.venus.carpapa.MainActivity;
import com.venus.carpapa.MapActivity;
import com.venus.carpapa.R;
import com.venus.carpapa.vo.CarSellDtoVo;

public class ChejianAdapter extends BaseAdapter {
	Context context;
	ArrayList<CarSellDtoVo> result;
	private LayoutInflater layoutInflater;
	String TAG = ChejianAdapter.class.getName();

	public ChejianAdapter(Context context, ArrayList<CarSellDtoVo> result) {
		this.context = context;
		this.result = result;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return result.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		holder mholder;
		if (convertView == null) {
			mholder = new holder();
			convertView = layoutInflater.inflate(R.layout.item_chejian, parent,
					false);
			mholder.noTextView = (TextView) convertView
					.findViewById(R.id.noTextView);
			mholder.timeTextView = (TextView) convertView
					.findViewById(R.id.timeTextView);
			mholder.addressTextView = (TextView) convertView
					.findViewById(R.id.addressTextView);
			mholder.statusTextView = (TextView) convertView
					.findViewById(R.id.statusTextView);
			mholder.phoneImageView = (ImageView) convertView
					.findViewById(R.id.phoneImageView);
			convertView.setTag(mholder);
		} else {
			mholder = (holder) convertView.getTag();
		}
		final CarSellDtoVo vo = result.get(position);
		if (vo != null) {
			try {
				mholder.noTextView.setText("车辆编号：" + vo.getCarSellCoding());

				// timeTextView.setText("预约时间："+vo.getTime());
				if (vo.getState() == 0) {
					mholder.statusTextView.setText("未审核");
				} else if (vo.getState() == 1) {
					mholder.statusTextView.setText("审核通过");
				} else if (vo.getState() == 2) {
					mholder.statusTextView.setText("审核未通过");
				}
				mholder.addressTextView.setText(vo.getAddress());
				mholder.noTextView
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								task(vo.getCarSellCoding(), vo.getCarId(),
										vo.getCarSellCoding());
							}
						});

				mholder.phoneImageView
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								start(vo.getTel());
							}
						});
				mholder.addressTextView
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								map(vo.getAddress());

							}
						});
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return convertView;
	}

	class holder {
		TextView noTextView, timeTextView, addressTextView, statusTextView;
		ImageView phoneImageView;
	}

	//
	private void task(String no, int carId, String carSellCoding) {

		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("no", no);
		intent.putExtra("carId", carId);
		intent.putExtra("carSellCoding", carSellCoding);

		this.context.startActivity(intent);

	}

	// 地图
	private void map(String phone) {

		Intent intent = new Intent(context, MapActivity.class);

		this.context.startActivity(intent);

	}

	// 电话
	private void start(String phone) {
		Uri uri = Uri.parse("tel:" + phone);

		Intent intent = new Intent(Intent.ACTION_DIAL, uri);

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.context.startActivity(intent);

	}
}
