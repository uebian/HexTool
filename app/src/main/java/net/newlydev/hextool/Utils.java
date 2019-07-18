package net.newlydev.hextool;

import java.security.*;
import java.io.*;

public class Utils
{
	public static String bytes2Hexstr(byte[] bytes)
	{
		String hex= "";
        if (bytes != null)
		{
            for (Byte b : bytes)
			{
                hex += String.format("%02X", b.intValue() & 0xFF).toUpperCase()+" ";
            }
        }
        return hex;
	}
	public static byte[] hexstr2Bytes(String hex){
		hex=hex.replaceAll(" ","");
		hex=hex.toLowerCase();
        int m = 0, n = 0;
        int byteLen = hex.length() / 2;
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }
	public static byte[] int2Bytes(int n)
	{  
		byte[] b = new byte[4];  
		b[3] = (byte) (n & 0xff);  
		b[2] = (byte) (n >> 8 & 0xff);  
		b[1] = (byte) (n >> 16 & 0xff);  
		b[0] = (byte) (n >> 24 & 0xff);  
		return b;  
	}
	public static int bytes2Int(byte[] bArr)
	{  
		if (bArr.length != 4)
		{  
			return -1;  
		}  
		return ((((bArr[3] & 0xff) << 24) | ((bArr[2] & 0xff) << 16) | ((bArr[1] & 0xff) << 8) | ((bArr[0] & 0xff) << 0)));   
	}
	public static byte[] short2Bytes(short n) {
		byte[] b=new byte[2];
        b[0] = (byte) (n >> 8);  
        b[1] = (byte) (n >> 0);
		return b;
    }
	
    public static short bytes2short(byte[] bytes) {  
        return (short) (((bytes[0] << 8) | bytes[1] & 0xff));  
    }  
	public static byte[] md5(byte[] bytes)
	{
		try
		{
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			return MD5.digest(bytes);
		}
		catch (NoSuchAlgorithmException e)
		{}
		return null;
	}
	public static String bytes2Utfstring(byte[] bytes)
	{
		try
		{
			return new String(bytes, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		return null;
	}
	public static byte[] utfstring2Bytes(String utfstr)
	{
		try
		{
			return utfstr.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		return null;
	}
	public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }
	public static String bytes2BinaryString(byte[] bytes)
	{
		String res="";
		for(byte b:bytes)
		{
			res =res+ (Integer.toBinaryString((b & 0xFF) + 0x100).substring(1))+" ";
		}
		return res;
	}
}
