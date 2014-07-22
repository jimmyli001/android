package com.venus.carpapa.act.info;

import java.util.Date;

import net.tsz.afinal.core.AsyncTask;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.venus.carpapa.R;
import com.venus.carpapa.util.DateUtil;
import com.venus.carpapa.util.DialogUtil;
import com.venus.carpapa.util.HttpUtil;
import com.venus.carpapa.vo.CarSellInfoVo;

public class InfoDetailFrame extends Fragment {
	RelativeLayout shouxu;
	RelativeLayout peizhi;
	RelativeLayout beizhu;
	EditText dengjiDate;
	private static InfoDetailFrame mDetailFrame;
	private Spinner sp_drivingLicense, sp_registration, sp_isUseridnumber,
			sp_useProperties, sp_carSource, sp_maintenance, sp_carLocation,
			sp_effluentStandard,sp_gearbox;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_info_right, container, false);
	}

	public static InfoDetailFrame newsInstance(int carId) {
		mDetailFrame = new InfoDetailFrame();
		Bundle mBundle = new Bundle();
		mBundle.putInt("carId", carId);
		mDetailFrame.setArguments(mBundle);
		return mDetailFrame;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		sp_drivingLicense = (Spinner) view
				.findViewById(R.id.spinner_drivingLicense);
		sp_registration = (Spinner) view
				.findViewById(R.id.spinner_registration);
		sp_isUseridnumber = (Spinner) view
				.findViewById(R.id.spinner_isUseridnumber);
		sp_useProperties = (Spinner) view.findViewById(R.id.sp_useProperties);
		sp_carSource = (Spinner) view.findViewById(R.id.sp_carSource);
		sp_carLocation = (Spinner) view.findViewById(R.id.sp_carLocation);
		sp_maintenance = (Spinner) view.findViewById(R.id.sp_maintenance);
		sp_effluentStandard = (Spinner) view
				.findViewById(R.id.sp_effluentStandard);
		sp_gearbox = (Spinner) view
				.findViewById(R.id.sp_gearbox);

		setspinner(sp_drivingLicense, R.array.InfodrivingLicense);
		setspinner(sp_registration, R.array.InfodrivingLicense);
		setspinner(sp_isUseridnumber, R.array.infoisUseridnumber);
		setspinner(sp_useProperties, R.array.infouseProperties);
		setspinner(sp_carSource, R.array.infocarSource);
		setspinner(sp_carLocation, R.array.infosp_carLocation);
		setspinner(sp_maintenance, R.array.infomaintenance);
		setspinner(sp_effluentStandard, R.array.infoeffluentStandard);
		setspinner(sp_gearbox, R.array.infogearbox);
	}

	private void setspinner(Spinner s, int array) {
		String[] mItems = getResources().getStringArray(array);
		// 建立Adapter并且绑定数据源
		ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, mItems);
		// 绑定 Adapter到控件
		s.setAdapter(_Adapter);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setView();
		setListener();
		LoadData();

	}

	private void LoadData() {
		// TODO Auto-generated method stub
		new AsyncTask<Object, Object, CarSellInfoVo>() {

			@Override
			protected CarSellInfoVo doInBackground(Object... arg0) {
				// TODO Auto-generated method stub
				return HttpUtil.getDateInfo4JSON(mDetailFrame.getArguments()
						.getInt("carId"));
			}

			protected void onPostExecute(CarSellInfoVo result) {
				Log.i("info", result.toString());
			};

		}.execute();

	}

	DatePickerDialog dpd;

	public void setView() {

		shouxu = (RelativeLayout) getView().findViewById(R.id.shouxu);
		peizhi = (RelativeLayout) getView().findViewById(R.id.peizhi);
		beizhu = (RelativeLayout) getView().findViewById(R.id.beizhu);
		dengjiDate = (EditText) getView().findViewById(R.id.dengjiDate);
		dengjiDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dpd.show();
			}
		});
		date_dialog();

	}

	public void date_dialog() {
		int year = Integer.parseInt(DateUtil.getYear());
		Date date = new Date();
		int month = date.getMonth();
		dpd = new DatePickerDialog(this.getActivity(),
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// System.out.println();
						dengjiDate.setText(year + "-" + monthOfYear// 注意：这里的数字是从0到11
								+ "-" + dayOfMonth);
					}
				}, year, month, DateUtil.getMM());
	}

	public void setListener() {

	}

	public void setShouxu() {
		shouxu.setVisibility(View.VISIBLE);
		peizhi.setVisibility(View.GONE);
		beizhu.setVisibility(View.GONE);
	}

	public void setPeizhi() {
		shouxu.setVisibility(View.GONE);
		peizhi.setVisibility(View.VISIBLE);
		beizhu.setVisibility(View.GONE);
	}

	public void setBeizhu() {
		shouxu.setVisibility(View.GONE);
		peizhi.setVisibility(View.GONE);
		beizhu.setVisibility(View.VISIBLE);
	}
}
