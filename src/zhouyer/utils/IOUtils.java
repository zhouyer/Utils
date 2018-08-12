/**  

* <p>Title: IOUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018年8月12日   

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
 * IO工具类
 */
public class IOUtils {
	/**
	 * 将字节数组中的数据保存在文件中
	 * 
	 * @param src
	 *            存储到的文件
	 * @param bs
	 *            字节数组
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
	 * 以字节的方式读取文件中的数据，存储到字节数组
	 * 
	 * @param src
	 * @return
	 * @throws IOException
	 * @return byte[]
	 */
	public static byte[] LoadBytes(File src) throws IOException {
		if (!src.exists())
			throw new IllegalArgumentException("源文件" + src + "不存在");
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
	 * 使用字节输入流（InputStream）和字节输出流（OutputStream）实现文件的拷贝功能
	 * 
	 * @param is
	 * @param os
	 * @return void
	 * @throws IOException
	 */
	public static void Copy(InputStream is, OutputStream os) throws IOException {
		// 设置一个缓冲
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
	 * 使用缓冲字节流（BufferedInputStream和BufferedOutputStream）实现文件的拷贝功能。
	 * 
	 * @return void
	 * @throws IOException 
	 */
	public static void CopyFile(File src, File dest) throws IOException {
		if (!src.exists())
			throw new IllegalArgumentException("源文件" + src + "不存在");
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
	 * 向基本输出流（OutputStream）中写入double数据
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
	 * 从基本输入流（InputStream）中，读取double数据
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
	 * 将字符串数组的每一项按行的形成写入到基本输出流
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
				bw.newLine();//写入行分隔符
			}
			bw.write(lines[lines.length-1]);
			bw.flush();
		}
	}
	
	/**
	 *  从基本输入流中成行读取文本存入字符串数组
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
