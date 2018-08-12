/**  

* <p>Title: TestIOUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018年8月12日   

*/
package zhouyer.utils.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import zhouyer.utils.IOUtils;


/**
 * @author zhouyer
 *
 */
public class TestIOUtils {
	public static void main(String[] args) {
		// TestSaveBytes();
		// byte[] b=TestLoadBytes();
		// System.out.println(Arrays.toString(b));
		// TestCopy();
		// TestCopyFile();
		// TestSaveDouble();
		//double res = TestLoadDouble();
		//System.out.println(res);
		//TestSaveLines();
		TestLoadLines();
	}

	public static void TestSaveBytes() {
		System.out.println("sfsdfsd");
		byte[] bytes = { 1, 2, 3, 4, 5 };
		try {
			IOUtils.SaveBytes(new File("F:/Study/test/bytes.dat"), bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static byte[] TestLoadBytes() {
		byte[] bs = null;
		try {

			bs = IOUtils.LoadBytes(new File("F:/Study/test/bytes.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bs;
	}

	public static void TestCopy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream("F:/Study/idea2017快捷键.txt");
			fos = new FileOutputStream("F:/Study/test/idea2017快捷键_copy.txt");
			IOUtils.Copy(fis, fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void TestCopyFile() {
		try {
			long startT = System.nanoTime();
			long startTime = System.currentTimeMillis();
			IOUtils.CopyFile(new File("F:/Study/tools/eclipse-jee-neon-2-win32-x86_64.zip"),
					new File("F:/Study/test/eclipse-jee-neon-2-win32-x86_64_copy.zip"));
			long endT = System.nanoTime();
			long endTime = System.currentTimeMillis();
			System.out.println("执行耗时：" + (endT - startT) + "ns");
			System.out.println("执行耗时：" + (endTime - startTime) + "ms");
			// 执行耗时：14919884811ns
			// 执行耗时：14921ms
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void TestSaveDouble() {
		double ip = Math.PI;
		System.out.println(ip);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("F:/Study/test/data.dat"));
			IOUtils.SaveDouble(ip, fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static double TestLoadDouble() {
		FileInputStream fis = null;
		double res = 0.0;
		try {
			fis = new FileInputStream(new File("F:/Study/test/data.dat"));
			res = IOUtils.LoadDouble(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return res;
	}

	public static void TestSaveLines() {
		String[] lines = { "java", "C#", "C++", "Hadoop" };
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("F:/Study/test/data.txt"));
			IOUtils.SaveLine(lines, fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void TestLoadLines(){
		String[] strs=null;
		FileInputStream fis= null;
		try {
			fis=new FileInputStream(new File("F:/Study/test/data.txt"));
			strs=IOUtils.LoadLine(fis);
			for(String str:strs){
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
