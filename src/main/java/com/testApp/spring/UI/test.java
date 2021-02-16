package com.testApp.spring.UI;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import sun.misc.BASE64Decoder;

@SuppressWarnings({ "deprecation", "restriction" })
public class test {

	public static void main(String[] args) throws Exception {
	    UIManager.setLookAndFeel(new NimbusLookAndFeel());
	    SwingUtilities.invokeAndWait(new Runnable() {
	        public void run() {
	            //new bug6937798();
	        }
	    });
	}
	
	public static String aesDecrypt(String content) {
	    try {

	    	byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
	       
	        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
	        SecretKeySpec keyspec = new SecretKeySpec(content.getBytes(), "AES");
	        IvParameterSpec ivspec = new IvParameterSpec(content.getBytes());

	        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

	        String originalString = new String(cipher.doFinal(encrypted1));
	        int length = originalString.length();
	        int ch = originalString.charAt(length - 1);
	        return originalString.substring(0, length - ch);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
	 