/**  

* <p>Title: TestFileUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018年8月10日   

*/
package zhouyer.utils.test;

import java.io.File;

import zhouyer.utils.FileUtils;

/**
 * 文件操作测试类
 * 
 * @author zhouyer
 *
 */
public class TestFileUtils {
	public static void main(String[] args) {
		//File file = new File("E:/大数据分析资料/共享平台数据确认");
		try {
			// FileUtils.DeleteDirectory(file);
			// FileUtils.ListDirectory(file);
			// List<File> list=FileUtils.ListFile(file, new
			// String[]{".txt",".docx"}, true);
			// for(File item:list){
			// System.out.println(item);
			// }
			File srcfile = new File("E:/大数据分析资料/sjcjrh");
			File destfile = new File("E:/大数据分析资料/Test");
			// FileUtils.CopyFile(srcfile, destfile);
			FileUtils.CopyDirectory(srcfile, destfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
