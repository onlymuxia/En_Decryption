package com.onlymuxia.test;

import java.io.UnsupportedEncodingException;

@SuppressWarnings("nls")
public final class HexUtil {

	public static byte[] hexStr2ByteArr(String strIn){
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static String byteToHex(byte arg) {
		int con = arg & 0xFF;
		String tmp = Integer.toHexString(con);
		if (tmp.length() % 2 == 0) {
			return tmp.toUpperCase();
		} else {
			return ("0" + tmp).toUpperCase();
		}
	}

	public static String intToHexByte(int arg) {
		int con = arg & 0xFF;
		String tmp = Integer.toHexString(con);
		if (tmp.length() % 2 == 0) {
			return tmp.toUpperCase();
		} else {
			return ("0" + tmp).toUpperCase();
		}
	}
	
	public static String shortToHex(int arg) {
		int int1 = arg & 0xFF;
		int int0 = (arg & 0xFF00)>>8;
		StringBuilder tmp = new StringBuilder();
		tmp.append(intToHexByte(int0));
		tmp.append(intToHexByte(int1));
		
		return tmp.toString().toUpperCase();
	}

	/*
	 * public static byte[] hexToBytes(String hex) { if
	 * (StringUtil.isHexString(hex)) { int len = hex.length() / 2; byte[] bt =
	 * new byte[len]; int tmp; for (int i = 0; i < len; i++) {
	 * //System.out.println(hex.substring(i * 2, i * 2 + 2)); tmp =
	 * Integer.parseInt(hex.substring(i * 2, i * 2 + 2),16);
	 * 
	 * bt[i] = (byte)tmp; //System.out.println(bt[i]); } return bt; }
	 * 
	 * return null; }
	 */

	public static String byte2Ucs2(byte[] data, int off, int length) {
		if (data == null || off + length > data.length)
			return null;

		byte[] rs = new byte[length];

		for (int i = 0; i < length; i++) {
			rs[i] = data[off + i];
		}

		String str = "";
		try {
			str = new String(rs, "UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str;
	}

	public static void main(String[] args) {
		System.out.println(intToHexByte(4444));
	}

	public static String byteArr2HexStr(byte[] arrB, String separator) { // separator
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			if (i != 0 && separator != null) {
				sb.append(separator);
			}

			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static String byteArr2Ascii(byte[] data) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			if (data[i] > 32 && data[i] < 127) {
				sb.append((char) data[i]);
			} else {
				sb.append(".");
			}

		}
		return sb.toString();
	}

	public static String byteArr2Ascii(byte[] data, int off, int length) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if ((off + i) >= data.length)
				break;

			sb.append((char) data[off + i]);

		}
		return sb.toString();
	}

	public static String byteArr2HexStr(byte[] arrB, int offset, int limit) {
		if(arrB.length<limit){
			limit = arrB.length;
		}

		StringBuffer sb = new StringBuffer(limit * 2);
		for (int i = offset; i < limit; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString().toUpperCase();
	}
	
	public static String toHexString(byte[] bt, int j, int k){
        StringBuffer sb = new StringBuffer();
        int limint = k>bt.length?bt.length:k;
        for(int i=j;i<limint;i++){
            int tmp = bt[i]&0xff;
            String tmpStr = Integer.toHexString(tmp);
            if(tmpStr.length()<2)
                sb.append("0");
            sb.append(tmpStr);
        }
        return sb.toString().toUpperCase();
    }
}
