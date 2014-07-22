package com.venus.carpapa.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.venus.carpapa.vo.CarSellDtoVo;
import com.venus.carpapa.vo.CarSellDtoVoList;
import com.venus.carpapa.vo.CarSellInfoVo;
import com.venus.carpapa.vo.ChildTargeList;
import com.venus.carpapa.vo.ChildTargeList_thread;
import com.venus.carpapa.vo.ChildTargetList_five;
import com.venus.carpapa.vo.ChildTargetList_secend;
import com.venus.carpapa.vo.ChildTrgeList_four;

/**
 * HttpUtil.java
 * <p>
 * 调用webservice Copyright: Copyright(c) 2013
 * 
 * 
 * @author Gavin_Feng
 *         </p>
 * 
 */
public class HttpUtil {

	public static String URL_WEBSERVICE_ENDPOINT = "http://218.244.147.229:8080/carpapa/services/Login?wsdl";
	public static String URL_WEBSERVICE_NameSpace = "http://218.244.147.229:8080/carpapa/services/Login";

	public static String ORDER_URL_WEBSERVICE_ENDPOINT = "http://218.244.147.229:8080/carpapa/services/CarOrder?wsdl";
	public static String ORDER_URL_WEBSERVICE_NameSpace = "http://218.244.147.229:8080/carpapa/services/CarOrder";

	public static String ORDER_URL_DATAINFO_ENDPOINT = "http://218.244.147.229:8080/carpapa/services/CarOrder?wsdl";
	public static String ORDER_URL_DATAINFO_NameSpace = "http://218.244.147.229:8080/carpapa/services/CarOrder";

	public static String ORDER_URL_CarAssess_ENDPOINT = "http://218.244.147.229:8080/carpapa/services/CarAssess?wsdl";
	public static String ORDER_URL_CarAssess_NameSpace = "http://218.244.147.229:8080/carpapa/services/CarAssess";

	public static String URL_WEBSERVICE_LOGIN = "login";
	public static String ORDER_URL_WEBSERVICE_LOGIN = "listPage";
	public static String ORDER_URL_CarAssess_LOGIN = "loadCarAssess";

	// public static String URL_WEBSERVICE_GET_CODE="getCode";
	// public static String URL_WEBSERVICE_LOGOUT="logout";
	// public static String URL_WEBSERVICE_SET_ORDER="setOrder";
	// public static String URL_WEBSERVICE_GET_ORDER_DETAIL="getOrderDetail";
	// public static String URL_WEBSERVICE_CANCEL_ORDER="cancelOrder";
	// public static String URL_WEBSERVICE_PAY_ORDER="payOrder";
	// public static String URL_WEBSERVICE_CONFIRM_ORDER="confirmOrder";
	// public static String URL_WEBSERVICE_IS_SERVICE="isService";
	// public static String
	// URL_WEBSERVICE_IS_SERVICE_BY_ADDR="isServiceByAddress";

	private static boolean DEBUG = true;
	static String tag = HttpUtil.class.getName();

	/**
	 * 
	 * @param NamePace
	 * @param Methods
	 * @param Url
	 * @param canshu
	 */
	public static String HttpU(String NamePace, String Methods, String Url,
			ArrayList<String> key, ArrayList<Object> volue) {
		String result = "";
		// 指定WebService的命名空间和调用的方法名
		SoapObject request = new SoapObject(NamePace, Methods);
		for (int i = 0; i < key.size(); i++) {
			request.addProperty(key.get(i), volue.get(i));
		}
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE transport = new HttpTransportSE(Url);
		transport.debug = true;
		try {
			// 调用WebService
			transport.call(NamePace + "#" + Methods, envelope);
			if (envelope.getResponse() != null) {
				result = envelope.getResponse().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Login
	 * */
	public static String login(String phone, String pwd) {

		String result = "";
		// 指定WebService的命名空间和调用的方法名
		SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace,
				URL_WEBSERVICE_LOGIN);
		request.addProperty("userName", phone);
		request.addProperty("password", pwd);
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);
		transport.debug = true;
		try {
			// 调用WebService
			transport.call(URL_WEBSERVICE_NameSpace + "#"
					+ URL_WEBSERVICE_LOGIN, envelope);
			if (envelope.getResponse() != null) {
				// SoapObject result1 = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject) result1
				// .getProperty(URL_WEBSERVICE_LOGIN + "Result");
				// result = detail.toString();
				result = envelope.getResponse().toString();
			}
			return result;
		} catch (Exception e) {

			return e.toString();
		}

	}

	/**
	 * 获得待检测的车辆list
	 * 
	 * @param userid
	 * @return
	 */

	public static String CarOrder(int userid) {
		ArrayList<String> key = new ArrayList<String>();
		key.add("userId");
		ArrayList<Object> volue = new ArrayList<Object>();
		volue.add(userid);
		return HttpU(ORDER_URL_WEBSERVICE_NameSpace,
				ORDER_URL_WEBSERVICE_LOGIN, ORDER_URL_WEBSERVICE_ENDPOINT, key,
				volue);

	}

	public static CarSellDtoVoList CarOrder4JSON(int userid) {

		CarSellDtoVoList list = new CarSellDtoVoList();
		try {
			JSONObject json = new JSONObject(CarOrder(userid));
			list.setSum(json.getInt("sum"));
			ArrayList<CarSellDtoVo> mCarSellDtoVo = new ArrayList<CarSellDtoVo>();
			JSONArray mArray = json.getJSONArray("carSellDtoList");
			for (int i = 0; i < mArray.length(); i++) {
				CarSellDtoVo c = new CarSellDtoVo();
				JSONObject obj = (JSONObject) mArray.get(i);
				c.setAddress(obj.getString("address"));
				c.setCarId(obj.getInt("carId"));
				c.setCarSellCoding(obj.getString("carSellCoding"));
				c.setCheckState(obj.getInt("checkState"));
				c.setLicenseNumber(obj.getString("licenseNumber"));
				c.setState(obj.getInt("state"));
				c.setTel(obj.getString("tel"));
				mCarSellDtoVo.add(c);
			}
			list.setCarSellDtoVo_List(mCarSellDtoVo);

		} catch (Exception e) {
			Log.i("tag", e.toString());
			e.printStackTrace();
		}
		return list;

	}

	public static String getDateInfo(int carId) {
		ArrayList<String> key = new ArrayList<String>();
		key.add("carId");
		ArrayList<Object> volue = new ArrayList<Object>();
		volue.add(carId);
		return HttpU(ORDER_URL_WEBSERVICE_NameSpace, "checkCarInfo",
				ORDER_URL_WEBSERVICE_ENDPOINT, key, volue);

	}

	public static CarSellInfoVo getDateInfo4JSON(int carId) {
		CarSellInfoVo mCarSellInfoVo = new CarSellInfoVo();
		try {
			JSONObject json = new JSONObject(getDateInfo(carId));
			JSONObject obj = json.getJSONObject("carSellInfo");
			mCarSellInfoVo.setCarId(obj.getInt("carId"));
			mCarSellInfoVo.setCarSellCoding(obj.getString("carSellCoding"));
			mCarSellInfoVo.setTel(obj.getString("tel"));
			mCarSellInfoVo.setAddress(obj.getString("address"));
			mCarSellInfoVo.setState(obj.getInt("state"));
			mCarSellInfoVo.setDataSourceType(obj.getInt("dataSourceType"));
			mCarSellInfoVo.setLicenseNumber(obj.getString("licenseNumber"));
			mCarSellInfoVo.setDrivingLicense(obj.getInt("drivingLicense"));
			mCarSellInfoVo.setRegistration(obj.getInt("registration"));
			mCarSellInfoVo.setCheckTime((Date) obj.get("checkTime"));
			mCarSellInfoVo.setTrafficInsuranceTime((Date) obj
					.get("trafficInsuranceTime"));
			mCarSellInfoVo.setCommlinsMoney(obj.getDouble("commlinsMoney"));
			mCarSellInfoVo.setCommercialLinesTime((Date) obj
					.get("commercialLinesTime"));
			mCarSellInfoVo.setIsUseridnumber(obj.getInt("isUseridnumber"));
			mCarSellInfoVo.setCarBigType(obj.getInt("carBigType"));
			mCarSellInfoVo.setCarBrand(obj.getInt("carBrand"));
			mCarSellInfoVo.setCarLine(obj.getInt("carLine"));
			mCarSellInfoVo.setCarType(obj.getInt("carType"));
			mCarSellInfoVo.setEngineNumber(obj.getString("engineNumber"));
			mCarSellInfoVo.setLicenceProvince(obj.getString("licenceProvince"));
			mCarSellInfoVo.setLicenceCity(obj.getString("licenceCity"));
			mCarSellInfoVo.setRegistrationTime((Date) obj
					.get("registrationTime"));
			mCarSellInfoVo.setCarAge(obj.getString("carAge"));
			mCarSellInfoVo.setUseProperties(obj.getInt("useProperties"));
			mCarSellInfoVo.setCarSource(obj.getInt("carSource"));
			mCarSellInfoVo.setMaintenance(obj.getInt("maintenance"));
			mCarSellInfoVo.setCarLocation(obj.getInt("carLocation"));
			mCarSellInfoVo.setDisplacement(obj.getDouble("displacement"));
			mCarSellInfoVo.setEffluentStandard(obj.getInt("effluentStandard"));
			mCarSellInfoVo.setGearbox(obj.getInt("gearbox"));
			mCarSellInfoVo.setLegend(obj.getDouble("legend"));
			mCarSellInfoVo.setDriveForm(obj.getInt("driveForm"));
			mCarSellInfoVo.setComment(obj.getString("comment"));
			mCarSellInfoVo.setPicture(obj.getString("picture"));
			mCarSellInfoVo.setCredentialsPicture(obj
					.getString("credentialsPicture"));
			mCarSellInfoVo.setAccidentPicture("accidentPicture");

		} catch (Exception E) {
			E.printStackTrace();
		}
		return mCarSellInfoVo;

	}

	public static ArrayList<ChildTargeList> getChildTargeList4JSON(String id) {
		ArrayList<ChildTargeList> mArrayList = new ArrayList<ChildTargeList>();

		try {
			ArrayList<String> key = new ArrayList<String>();
			key.add("carSellCoding");
			ArrayList<Object> volue = new ArrayList<Object>();
			volue.add(id);
			String str = HttpU(ORDER_URL_CarAssess_NameSpace, "loadCarAssess",
					ORDER_URL_CarAssess_ENDPOINT, key, volue);
			JSONObject mJsonObject = new JSONObject(str);
			JSONArray mJsonArray = mJsonObject.getJSONArray("assessList");
			return ischeaded(mJsonArray.getJSONObject(0), mArrayList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static ArrayList<ChildTargeList> ischeaded(JSONObject jsonObject,
			ArrayList<ChildTargeList> mChildTargeList_ist) {
		@SuppressWarnings("unchecked")
		Iterator<String> it = jsonObject.keys();
		while (it.hasNext()) {
			ChildTargeList mChildTargeList = new ChildTargeList();
			mChildTargeList.setTargetName(jsonObject.optString("targetName"));
			mChildTargeList.setLevel(jsonObject.optInt("level"));
			JSONArray array = jsonObject.optJSONArray("childTargetList");
			if (array != null) {
				ArrayList<ChildTargeList> bChildTargeList_ist = new ArrayList<ChildTargeList>();
				mChildTargeList
						.setmChildTargetList_secends(bChildTargeList_ist);
				for (int i = 0; i < array.length(); i++) {
					ischeaded(array.optJSONObject(i), bChildTargeList_ist);
				}
			} else {
				mChildTargeList.setChecked(jsonObject.optInt("checked"));
			}
			mChildTargeList_ist.add(mChildTargeList);
		}
		return mChildTargeList_ist;
	}

	// private List<TargetInfo> getAllTargetInfoTreeList(
	// List<TargetInfo> targetInfoTreeList) throws Exception {
	// List<TargetInfo> treeList = new ArrayList<TargetInfo>();
	// for (TargetInfo ti1 : targetInfoTreeList) {// 本级
	// List<TargetInfo> childList = new ArrayList<TargetInfo>();
	// for (TargetInfo ti2 : allTargetInfoList) {// 子级
	// if (ti1.getTargetId().intValue() == ti2.getParentId()
	// .intValue()) {
	// childList.add(ti2);
	// }
	// }
	// if (ti1.getCheckParts() != null
	// && ti1.getCheckParts().intValue() == 1) {
	// ti1.setChildTargetInfoList(childList);
	// } else {
	// ti1.setChildTargetInfoList(getAllTargetInfoTreeList(childList));
	// }
	// treeList.add(ti1);
	// }
	// return treeList;
	// }

	// public static String getCode(String phone){
	// Logger.d(tag, "getCode:"+phone);
	//
	// String result="";
	// // 指定WebService的命名空间和调用的方法名
	// SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace,
	// URL_WEBSERVICE_GET_CODE);
	// request.addProperty("mobile", phone);
	// // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
	// SoapSerializationEnvelope envelope = new
	// SoapSerializationEnvelope(SoapEnvelope.VER11);
	// envelope.bodyOut = request;
	// envelope.setOutputSoapObject(request);
	// HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);
	// transport.debug=true;
	// try {
	// // 调用WebService
	// transport.call(URL_WEBSERVICE_NameSpace+"#"+URL_WEBSERVICE_GET_CODE,
	// envelope);
	// if (envelope.getResponse() != null) {
	// result=envelope.getResponse().toString();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// Logger.d(tag, "getCode result:"+result);
	// return result;
	// }
	// public static String setOrder(int uid,String username,String orderjson){
	// Logger.d(tag, "uid:"+uid);
	// Logger.d(tag, "username:"+username);
	// Logger.d(tag, "orderjson:"+orderjson);
	// String result="";
	// // 指定WebService的命名空间和调用的方法名
	// SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace,
	// URL_WEBSERVICE_SET_ORDER);
	// request.addProperty("uid", uid);
	// request.addProperty("username", username);
	// request.addProperty("orderjson", orderjson);
	// // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
	// SoapSerializationEnvelope envelope = new
	// SoapSerializationEnvelope(SoapEnvelope.VER11);
	// envelope.bodyOut = request;
	// envelope.setOutputSoapObject(request);
	// HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);
	// transport.debug=true;
	// try {
	// // 调用WebService
	// transport.call(URL_WEBSERVICE_NameSpace+"#"+URL_WEBSERVICE_SET_ORDER,
	// envelope);
	// if (envelope.getResponse() != null) {
	// result=envelope.getResponse().toString();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return result;
	// }
	// public static String logout(String userId, String userPhone) {
	// String result="";
	// // 指定WebService的命名空间和调用的方法名
	// SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace,
	// URL_WEBSERVICE_LOGOUT);
	// request.addProperty("uid", userId);
	// request.addProperty("username", userPhone);
	// // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
	// SoapSerializationEnvelope envelope = new
	// SoapSerializationEnvelope(SoapEnvelope.VER11);
	// envelope.bodyOut = request;
	// envelope.setOutputSoapObject(request);
	// HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);
	// transport.debug=true;
	// try {
	// // 调用WebService
	// transport.call(URL_WEBSERVICE_NameSpace+"#"+URL_WEBSERVICE_LOGOUT,
	// envelope);
	// if (envelope.getResponse() != null) {
	// result=envelope.getResponse().toString();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return result;
	// }

	/**
	 * 调动服务器webservice接口
	 * 
	 * @param methodName
	 *            接口方法名称
	 * @param params
	 *            接口参数
	 * @return
	 * @throws Exception
	 */
	public static Object getInfoFromServer(String methodName,
			Map<String, Object> params) throws Exception {
		Object result = null;
		// 指定WebService的命名空间和调用的方法名
		SoapObject request = new SoapObject(URL_WEBSERVICE_NameSpace,
				methodName);

		for (Iterator<Map.Entry<String, Object>> it = params.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, Object> m = it.next();
			request.addProperty(m.getKey(), m.getValue());
		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE transport = new HttpTransportSE(URL_WEBSERVICE_ENDPOINT);
		transport.debug = DEBUG;
		// 调用WebService
		transport.call(URL_WEBSERVICE_NameSpace + "#" + methodName, envelope);
		if (envelope.getResponse() != null) {
			result = envelope.getResponse();
		}
		return result;
	}

}
