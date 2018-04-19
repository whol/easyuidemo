package com.wang.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wang.model.common.PageData;

public class StringUtil {
	public static void main(String[] args) {
		String s = "简介";
		// String tt = gbEncoding(s);
		// String tt1 = "你好，我想给你说一个事情";
		// System.out
		// .println(decodeUnicode("\"content\":\"{\"result\":{\"receiveTimeNumber\":1435715475483,\"gmtModify\":\"2015-07-01 09:51:15\",\"payTime\":\"2015-07-01 09:51:15\",\"parentBizOrderId\":192549201695318,\"auctionName\":\"\u706f\u5177\u9c7c\u7ebf\u540a\u706f\u8fd0\u8d39\u6a21\u677f\",\"sellerNick\":\"tworkb10057\",\"buyerMail\":\"hav_ieflv_jgq@123.com\",\"auctionId\":2100542224706,\"buyerNick\":\"zy_test_012\",\"payTimeNumber\":1435715475483,\"tpName\":\"sandbox_agogd\",\"categoryId\":131758542,\"auctionSkuProperties\":\"{\\\"@type\\\":\\\"java.util.HashMap\\\",\\\"\u706f\u5934\u6570(\u4e2a)\\\":\\\"3\\\",\\\"\u706f\u7f69\u4e3b\u6750\u8d28\\\":\\\"\u5408\u91d1\\\",\\\"\u7236\u7c7b\u76ee\\\":\\\"\u540a\u706f\\\",\\\"\u7c7b\u76ee\\\":\\\"\u9c7c\u7ebf\u540a\u706f\\\",\\\"\u957f\u5ea6(cm)\\\":\\\"1\\\",\\\"\u9ad8\u5ea6(cm)\\\":\\\"15-25\\\"}\",\"sellerPhone\":\"057134611425\",\"serviceOrderId\":192549201705318,\"taskMemo\":\"\u4e0a\u95e8\u5b89\u88c5\",\"auctionPrice\":11100,\"buyerMobile\":\"13584843012\",\"shopName\":\"tworkb10057\u5e97\u94fa\",\"receiveTime\":\"2015-07-01 09:51:15\",\"tpId\":3651882518,\"bizOrderId\":192549201715318,\"taskType\":0,\"serviceCount\":1,\"buyerAddress\":\"\u5317\u4eac \u5317\u4eac\u5e02 \u4e1c\u57ce\u533a \u5b89\u5b9a\u95e8\u8857\u9053cshi cshi cshi cshi cshi cshi\",\"serviceName\":\"\u706f\u5177\u5b89\u88c5\",\"id\":1435715475483,\"orderRelationId\":1100009681,\"name\":\"\u706f\u5177\u5b89\u88c5\",\"acceptType\":0,\"gmtCreate\":\"2015-07-01 09:51:15\",\"buyerId\":3595851853,\"buyerName\":\"fengyan\",\"taskStatus\":1,\"sellerId\":2049342226,\"buyerZipCode\":\"045100\",\"brand\":\"\u706f\u5177\u54c1\u724c\",\"attributes\":{\"lbxNo\":\"LP00012015478318\"}}}\",\"id\":\"4091000003050732052\",\"pub_app_key\":\"4272\",\"pub_time\":\"2015-07-01 09:51:15\",\"topic\":\"tmall_fuwu_WorkcardInfo\",\"user_id\":\"3651882518\",\"user_nick\":\"sandbox_agogd\"}},\"request_id\":\"9wy20xlfryhi\"}"));
		// System.out.println(decodeUnicode(tt1));
		// System.out.println(HTMLDecoder.decode("中国"));
		// String s1 = "\u7b80\u4ecb";
		// System.out.println(s.indexOf("\\"));
		//System.out.println(isLong("00 "));
		String[] str={"","1234","1.0","0.1","-0.1","-0.0","abc","2015-10"};
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]+"//"+isNumeric(str[i])+"//"+isDouble(str[i]));
		}
	}

	public static boolean isNumeric(String str) {	
		if (str==null||"".equals(str)) {
			return false;
		}else if(str.startsWith("-")){
			str=str.substring(1);
		}
		Pattern pattern = Pattern.compile("[0-9]+.*[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		if (str!=null &&(str.indexOf("-")>=0||str.indexOf("%")>=0)) {
			return false;
		}
		return true;
	}
	public static boolean isDouble(String str) {		
		if (str==null||"".equals(str)) {
			return false;
		}else if(str.startsWith("-")){
			str=str.substring(1);
		}
		Pattern pattern = Pattern.compile("\\d+\\.\\d+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		if (str!=null &&(str.indexOf("-")>=0||str.indexOf("%")>=0)) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * 功能描述：判断字符串是否是Long.
	 * 
	 * @param str
	 * @return
	 * 
	 * @author Administrator
	 * 
	 * @since 2015-8-13
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isLong(String str) {
		boolean flag = true;
		if (str != null) {
			try {
				Long.parseLong(str);
			} catch (Exception e) {
				return false;
			}
		}
		return flag;
	}

	/**
	 * 
	 * 功能描述：工单导出使用.
	 * 
	 * @param str
	 * @return
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-9-21
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String isNullObject(String str) {
		if (str == null || "null".equals(str) || "NULL".equals(str)) {
			return "";
		} else {
			return str.trim();
		}
	}

	public static double isNullNum(String str) {
		if (str == null || "null".equals(str) || "NULL".equals(str)) {
			return 0;
		} else {
			return Double.parseDouble(str.trim());
		}
	}

	/**
	 * 是否为空.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		boolean flag = true;
		if (str != null) {
			if (!"".equals(str)) {
				return false;
			}
		}
		return flag;
	}

	/**
	 * 
	 * 功能描述：
	 * 
	 * @param list
	 * @return
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-10-9
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static int getListSize(List list) {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	/**
	 * Unicode转UTF-8.
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';

					else if (aChar == 'n')

						aChar = '\n';

					else if (aChar == 'f')

						aChar = '\f';

					outBuffer.append(aChar);

				}

			} else

				outBuffer.append(aChar);

		}

		return outBuffer.toString();

	}

	public static PageData getPeriod() {
		PageData pd = new PageData();
		pd.put(Common.RING_TYPE_MONTH, "月");
		pd.put(Common.RING_TYPE_WEEK, "周");
		pd.put(Common.RING_TYPE_DAY, "日");
		return pd;
	}
	public static List<String> getHours() {
		List<String> list =new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			list.add(i<10?"0"+i:""+i);
		}
		return list;
	}
	public static String getPeriod(String ringtype) {
		String title = "月份";
		if (ringtype.equals(Common.RING_TYPE_WEEK)) {
			title = "周";
		} else if (ringtype.equals(Common.RING_TYPE_DAY)) {
			title = "日";
		}
		return title;
	}

	public static PageData getEstimateLevel() {
		PageData pd = new PageData();
		pd.put(5, "最佳");
		pd.put(4, "良好");
		pd.put(3, "合格");
		pd.put(2, "较差");
		pd.put(1, "不满意");
		return pd;
	}
}
