/**  

* <p>Title: IOUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018��8��12��   

*/
package zhouyer.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyer
 * IO������
 */
public class IOUtils {
	/**
	 * ���ֽ������е����ݱ������ļ���
	 * 
	 * @param src
	 *            �洢�����ļ�
	 * @param bs
	 *            �ֽ�����
	 * @return void
	 * @throws IOException
	 */
	public static void SaveBytes(File src, byte[] bs) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(src);
			fos.write(bs);
			// fos.write(bs, 0, bs.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null)
				fos.close();
		}
	}

	/**
	 * ���ֽڵķ�ʽ��ȡ�ļ��е����ݣ��洢���ֽ�����
	 * 
	 * @param src
	 * @return
	 * @throws IOException
	 * @return byte[]
	 */
	public static byte[] LoadBytes(File src) throws IOException {
		if (!src.exists())
			throw new IllegalArgumentException("Դ�ļ�" + src + "������");
		FileInputStream fis = null;
		byte[] bs = null;
		try {
			fis = new FileInputStream(src);
			bs = new byte[fis.available()];
			fis.read(bs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}

		return bs;
	}

	/**
	 * ʹ���ֽ���������InputStream�����ֽ��������OutputStream��ʵ���ļ��Ŀ�������
	 * 
	 * @param is
	 * @param os
	 * @return void
	 * @throws IOException
	 */
	public static void Copy(InputStream is, OutputStream os) throws IOException {
		// ����һ������
		byte[] buffer = new byte[4 * 1024];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			System.out.println(len);
			os.write(buffer, 0, buffer.length);
		}
		if (is != null)
			is.close();
		if (os != null)
			os.close();
	}

	/**
	 * ʹ�û����ֽ�����BufferedInputStream��BufferedOutputStream��ʵ���ļ��Ŀ������ܡ�
	 * 
	 * @return void
	 * @throws IOException 
	 */
	public static void CopyFile(File src, File dest) throws IOException {
		if (!src.exists())
			throw new IllegalArgumentException("Դ�ļ�" + src + "������");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(dest));
			int len = -1;
			while ((len = bis.read()) != -1) {
				bos.write(len);
			}
			bos.flush();
		} finally {

			try {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ������������OutputStream����д��double����
	 * 
	 * @param data
	 * @param dest
	 * @return void
	 * @throws IOException 
	 */
	public static void SaveDouble(Double data,OutputStream dest) throws IOException{
		DataOutputStream dos= new DataOutputStream(dest);
		dos.writeDouble(data);
		dos.flush();
	}
	
	/**
	 * �ӻ�����������InputStream���У���ȡdouble����
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 * @return double
	 */
	public static double LoadDouble(InputStream is) throws IOException{
		DataInputStream dis= new DataInputStream(is);
		return dis.readDouble();
	}
	
	/**
	 * ���ַ��������ÿһ��е��γ�д�뵽���������
	 * @param lines
	 * @param dest
	 * @return void
	 * @throws IOException 
	 */
	public static void SaveLine(String[] lines,OutputStream dest) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(dest));
		if(lines!=null&&lines.length>0){
			for(int i=0;i<lines.length-1;i++){
				bw.write(lines[i]);
				bw.newLine();//д���зָ���
			}
			bw.write(lines[lines.length-1]);
			bw.flush();
		}
	}
	
	/**
	 *  �ӻ����������г��ж�ȡ�ı������ַ�������
	 * @param is
	 * @return
	 * @throws IOException
	 * @return String[]
	 */
	public static String[] LoadLine(InputStream is) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(is));
		List<String> list= new ArrayList<String>();
		String line=null;
		while((line=br.readLine())!=null){
			list.add(line);
		}
		
		return list.toArray(new String[]{});
	}
}
