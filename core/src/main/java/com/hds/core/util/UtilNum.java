package com.hds.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UtilNum {
	public static double max(int precision,double... nums) {
		double max = nums[0];
		for(int i = 1;i < nums.length;i++){
			if(myRound(nums[i] - max,precision) > 0)
				max = nums[i];
		}
		return max;
	}
	
	public static double max(double... nums) {
		return max(6,nums);
	}
	
	public static double min(int precision,double... nums) {
		double min = nums[0];
		for(int i = 1;i < nums.length;i++){
			if(myRound(nums[i] - min,precision) < 0)
				min = nums[i];
		}
		return min;
	}
	
	public static double min(double... nums) {
		return min(6,nums);
	}
		
	public static Double myRound(Double dValue, int roundMode){
		if(dValue == null)
			return null;
		if(dValue.compareTo(0.0) > 0)
			dValue = dValue + 0.0000000001;
		else
			dValue = dValue - 0.0000000001;
				
		BigDecimal b = new BigDecimal(dValue);
		return b.setScale(roundMode, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static Double myRoundDown(Double dValue, int roundMode){
		if(dValue == null)
			return null;
		if(dValue.compareTo(0.0) > 0)
			dValue = dValue + 0.0000000001;
		else
			dValue = dValue - 0.0000000001;

		BigDecimal b = new BigDecimal(dValue);
		return b.setScale(roundMode, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	public static Double myRoundUp(Double dValue, int roundMode){
		if(dValue == null)
			return null;
		if(dValue.compareTo(0.0) > 0)
			dValue = dValue + 0.0000000001;
		else
			dValue = dValue - 0.0000000001;

		BigDecimal b = new BigDecimal(dValue);
		return b.setScale(roundMode, BigDecimal.ROUND_UP).doubleValue();
	}
	
	public static Double objToDouble(Object obj){
		if(obj == null)
			return null;
		if(obj.getClass().equals(Double.class))
			return (Double)obj;
		else if(obj.getClass().equals(String.class))
			return Double.parseDouble((String)obj);
		else
			return ((Number)obj).doubleValue();
	}
	
	public static Integer objToInteger(Object obj){
		if(obj == null)
			return null;
		
		if(obj.getClass().equals(Integer.class))
			return (Integer)obj;
		if(obj.getClass().equals(String.class))
			return Integer.parseInt((String)obj);
		else
			return ((Number)obj).intValue();
	}

	public static Long objToLong(Object obj) {
		if (obj == null)
			return null;
		if (obj.getClass().equals(Long.class))
			return (Long) obj;
		else if (obj.getClass().equals(String.class))
			return Long.parseLong((String) obj);
		else
			return ((Number) obj).longValue();
	}
	
	public static String toStringRound(double x, int n) {
		if (x < 0.00)
			x = x - 0.0000000001;
		else
			x = x + 0.0000000001;
		BigDecimal bd = new BigDecimal(x);
		bd = bd.setScale(n, BigDecimal.ROUND_HALF_UP);
		return bd.toString();
	}	
	public static boolean integerYesNo(Integer value){
		return integerIn(value,0,1);
	}
	public static boolean integerIn(Integer value, int ...values){
		if (value == null)
			return false;
		
		if (values != null && values.length > 0){
			for(int i = 0;i < values.length;i ++){
				if (value.intValue() == values[i]){
					return true;
				}
			}
			return false;
		}else
			return true;
	}
	public static boolean integerBiggerOrEqual(Integer value, int minValue){
		if (value == null)
			return false;
		
		if (value.intValue() >= minValue){
			return true;
		}
		return false;
	}
	public static boolean integerSmallerOrEqual(Integer value, int maxValue){
		if (value == null)
			return false;
		
		if (value.intValue() <= maxValue){
			return true;
		}
		return false;
	}
	
	public static boolean intEqual(Integer a, Integer b){
		if(a == null && b == null)
			return true;
		else if((a != null && b == null) || (a == null && b != null))
			return false;
		else
			return a.equals(b);
		
	}
	
	public static boolean doubleEqual(Double a, Double b){
		return doubleEqual(a,b,6);
	}
	
	public static boolean doubleEqual(Double a, Double b, int precision){
		if(a == null && b == null)
			return true;
		else if((a != null && b == null) || (a == null && b != null))
			return false;
		else
			return myRound(a,precision).equals(myRound(b,precision));
	}
	
//	/**
//	 * @throws Exception 
//	 * @方法说明:	检查Double数值是否合法，不可是NULL，不可小于0	
//	 * @修改人员：	SANQ
//	 * @修改日期：	Oct 29, 2014	4:07:36 PM
//	 */
//	public static boolean checkDoubleNON(Double dValue){
//		if (checkDouble(dValue) > 0)
//			return true ;
//		else 
//			return false;
//	}
	
	/**
	 * @方法说明:	检查Double数值是否合法，NULL返回一个特殊的小于0的整数，其它返回compareTo（0.00）的结果。
	 * @修改人员：	SANQ
	 * @修改日期：	Oct 29, 2014	4:08:20 PM
	 */
	public static int checkDouble(Double dValue){
		if (dValue == null)
			return -100000000;
		
		return dValue.compareTo(0.00);
	}
	public static Double myRoundPrice(Double dValue){
		return myRound(dValue,6);
	}
	public static Double myRoundNum(Double dValue){
		return myRound(dValue,3);
	}	
	public static Double myRoundMoney(Double dValue){
		return myRound(dValue,2);
	}
	public static Double myRoundCanSaleDays(Double dValue){
		return myRound(dValue,1);
	}
	
	public static int checkPrice(Double price) throws Exception {
		if (price == null)
			throw new Exception("价格不可为空");
		
		return myRoundPrice(price).compareTo(0.000000);
	}
	public static int checkNum(Double num) throws Exception {
		if (num == null)
			throw new Exception("数量不可为空");
		
		return myRoundNum(num).compareTo(0.000);
	}
	
	public static boolean isBetween(Double num, Double lower, Double upper, int precision){
		
		if(myRound(num - lower,precision).compareTo(0.0) < 0)
			return false;
		
		if(myRound(num - upper,precision).compareTo(0.0) > 0)
			return false;
		
		return true;
	}
	
	public static boolean isBetween(Integer num, Integer lower, Integer upper, int precision){
		
		if((num.intValue() - lower.intValue()) < 0)
			return false;
		
		if((num.intValue() - upper.intValue()) > 0)
			return false;
				
		return true;
	}
	
	public static int checkMoney(Double money) throws Exception {
		if (money == null)
			throw new Exception("金额不可为空");
		
		return myRoundMoney(money).compareTo(0.00);
	}
	public static ArrayList<String> money2StrArray(Double value){
		return double2StrArray(value,2);
	}
	public static ArrayList<String> double2StrArray(Double value, int percision){
		value = myRound(value,percision);
		int b = 1;
		for (int i = 0 ; i < percision ; i ++){
			b = b * 10;
		}
		value = myRound(value * b,0);
		String str = String.valueOf(value.longValue());
		if (str == null){
			return null;
		}
		str = str.trim();
		
		ArrayList<String> s = new ArrayList<String>();
		//倒序排列放入数组；
		for (int i = str.length() - 1; i >= 0 ; i --){
			s.add(str.substring(i,i + 1));
		}
		return s;
	}
//	public static ArrayList<String> money2ChinaStrArray(Double value){
//		return double2ChinaStrArray(value,2);
//	}
//	public static ArrayList<String> double2ChinaStrArray(Double value, int percision){
//		ArrayList<String> s = double2StrArray(value,percision);
//		for (int i = 0; i < s.size() ; i ++){
//			String x = s.get(i);
//			if (x.equals("-"))
//				continue;
//			s.set(i,UtilChineseMoney.bigMoneyBit(x));
//		}
//		return s;
//	}
	
	/**
	 * 订货数量，要货数量等按规格取整,传入参数(数量,规格) By SANQ 16.3.10
	 */
	public static Double getStdQuantity(Double quantity, Double std) throws Exception {
		if (quantity == null)
			return null;
		if (std == null)
			return null;
		if (checkNum(std) <= 0)
			std = 1.0;
		return UtilNum.myRoundNum(UtilNum.myRound(quantity / std, 0) * std);
	}
	/**
	 * 按比例分配数量并按规格取整,传入参数(数量,比率,规格) By SANQ 16.3.10
	 */
	public static Double getStdQuantity(Double quantity, Double rate, Double std) throws Exception {
		if (quantity == null || rate == null)
			return null;
		if (checkNum(quantity) < 0){
			return null;
		}
		if (checkPrice(rate) < 0)
			return null;
		if (checkPrice(rate) == 0)
			return 0.0;
		
		if (std == null)
			std = 1.0;
		return getStdQuantity(UtilNum.myRoundDown(quantity * rate,0),std);
	}


	/**
	 * double转String,保留小数点后两位
	 * @param num
	 * @return
	 */
	public static String doubleToString(double num){
		//使用0.00不足位补0，#.##仅保留有效位
		return new DecimalFormat("0.00").format(num);
	}


	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		Object obj = -2.5551;
//		double d = objToDouble(obj);
//		System.out.println( UtilNum.myRound(d,3));
//		System.out.println( UtilNum.myRoundDown(d,3));
//		System.out.println( UtilNum.myRoundUp(d,3));
//		
		Double d = -0.00000000000000000001;
		System.out.println(d.compareTo(0.0));

		System.out.println("10 1\t" + UtilNum.getStdQuantity(10.0,1.0));
		System.out.println("10 2\t" + UtilNum.getStdQuantity(10.0,2.0));
		System.out.println("10 3\t" + UtilNum.getStdQuantity(10.0,3.0));
		System.out.println("10 3.5\t" + UtilNum.getStdQuantity(10.0,3.5));
		System.out.println("10 4.0\t" + UtilNum.getStdQuantity(10.0,4.0));
		System.out.println("10 6.0\t" + UtilNum.getStdQuantity(10.0,6.0));
		System.out.println("10 7.0\t" + UtilNum.getStdQuantity(10.0,7.0));
		System.out.println("10 8.0\t" + UtilNum.getStdQuantity(10.0,8.0));
		System.out.println("10 9.0\t" + UtilNum.getStdQuantity(10.0,9.0));
		
		
		
//		ArrayList<String> s = money2ChinaStrArray(x);
//		
//		ArrayList<String> ss = money2StrArray(x);
//		MyMap<String,Object> m = new MyLinkedHashMap<String,Object>();
//		
//		for (int i = 0; i < ss.size() ; i ++){
//			String key = "money" + String.valueOf(i);
//			m.put(key, ss.get(i));
//		}
//		String key = "money" + String.valueOf(ss.size());
//		m.put(key, "￥");
//		System.out.println(d.compareTo(0.0));
//		System.out.println(s);
	}
}
