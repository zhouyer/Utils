/**  

* <p>Title: TestFileUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018��8��10��   

*/
package zhouyer.utils.test;

import java.io.File;

import zhouyer.utils.FileUtils;

/**
 * �ļ�����������
 * 
 * @author zhouyer
 *
 */
public class TestFileUtils {
	public static void main(String[] args) {
		//File file = new File("E:/�����ݷ�������/����ƽ̨����ȷ��");
		try {
			// FileUtils.DeleteDirectory(file);
			// FileUtils.ListDirectory(file);
			// List<File> list=FileUtils.ListFile(file, new
			// String[]{".txt",".docx"}, true);
			// for(File item:list){
			// System.out.println(item);
			// }
			File srcfile = new File("E:/�����ݷ�������/sjcjrh");
			File destfile = new File("E:/�����ݷ�������/Test");
			// FileUtils.CopyFile(srcfile, destfile);
			FileUtils.CopyDirectory(srcfile, destfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
