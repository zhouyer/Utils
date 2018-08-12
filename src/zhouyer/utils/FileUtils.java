/**  

* <p>Title: FileUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018年8月10日   

*/
package zhouyer.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * 
 * @author zhouyer
 *
 */
public class FileUtils {
	/**
	 * 删除指定目录(文件夹)及包含的所有内容
	 * 
	 * @param dir
	 * @return void
	 * @throws IOException
	 */
	public static void DeleteDirectory(File dir) throws IOException {
		// 判断目录是否存在
		if (!dir.exists()) {
			throw new IllegalArgumentException("目录：【" + dir + "】不存在");
		}

		// 判断是否是目录
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("【" + dir + "】不是文件夹");
		}

		// 获取目录下面的所有目录及文件
		File[] files = dir.listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					DeleteDirectory(file);
				} else {
					System.out.print(file);
					if (!file.delete()) {
						throw new IOException("无法删除文件：" + file + "");
					} else {
						System.out.println(file + "文件删除成功");
					}
				}
			}

		}

		System.out.println(dir);
		if (!dir.delete()) {
			throw new IOException("无法删除目录：" + dir);
		} else {
			System.out.println(dir + "文件夹删除成功");
		}

	}

	/**
	 * 列出该目录下面的所有目录及文件
	 * 
	 * @param dir
	 * @return void
	 */
	public static void ListDirectory(File dir) {
		if (!dir.exists())
			throw new IllegalArgumentException("目录：【" + dir + "】不存在");
		if (!dir.isDirectory())
			throw new IllegalArgumentException("【" + dir + "】不是目录");
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory())
					ListDirectory(file);
				else
					System.out.println(file);
			}
		}
	}

	/**
	 * 列出指定目录下指定扩展名的所有文件。用户可以指定多个扩展名；用户也可以选择是否列出 子目录下的文件
	 * 
	 * @param dir
	 * @param ff
	 * @param recursive
	 * @return void
	 */
	public static List<File> ListFile(File dir, FileFilter ff, boolean recursive) {
		List<File> fileList = new ArrayList<File>();
		File[] files = dir.listFiles(ff);
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isFile()) // 如果是文件，就加入list中
					fileList.add(file);
				else {
					if (recursive) { // 选择子目录下的文件
						// 添加子目录中的经过过滤的所有文件添加到list
						fileList.addAll(ListFile(file, ff, true));
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 列出指定目录下指定扩展名的所有文件。用户可以指定多个扩展名；用户也可以选择是否列出 子目录下的文件
	 * 
	 * @param dir
	 * @param extensions
	 * @param recursive
	 * @return
	 * @return List<File>
	 */
	public static List<File> ListFile(File dir, final String[] extensions, boolean recursive) {
		if (!dir.exists())
			throw new IllegalArgumentException("目录：【" + dir + "】不存在");
		// 判断是否是目录
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("【" + dir + "】不是文件夹");
		}

		// 文件过滤器
		FileFilter ff = null;
		// 如果没有扩展名，就不做过滤操作
		if (extensions == null || extensions.length == 0) {
			ff = new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					return true;// 相当于不进行过滤
				}
			};
		} else {// 有扩展名，进行过滤操作
			ff = new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					if (pathname.isDirectory())
						return true;
					String name = pathname.getName();
					for (String ext : extensions) {
						if (name.endsWith(ext))
							return true;
					}
					return false;
				}
			};
		}

		return ListFile(dir, ff, recursive);

	}

	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 *            被拷贝的文件
	 * @param destFile
	 *            拷贝成功的文件
	 * @throws IOException
	 * @return void
	 */
	public static void CopyFile(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists())
			throw new IllegalArgumentException("目录：【" + srcFile + "】不存在");
		// 判断是否是目录
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException("【" + srcFile + "】不是文件");
		}

		if (destFile.isDirectory())
			destFile = new File(destFile, srcFile.getName());

		// 判断目标文件文件是否存在，不存在就创建
		if (!destFile.exists()) {
			if (!destFile.createNewFile()) {
				throw new IOException("无法创建文件【" + destFile + "】");
			}
		}
		RandomAccessFile srcRaf = new RandomAccessFile(srcFile, "r");
		RandomAccessFile destRaf = new RandomAccessFile(destFile, "rw");
		byte[] buffer = new byte[8 * 1024];
		int len = -1;
		while ((len = srcRaf.read(buffer)) != -1) {
			destRaf.write(buffer, 0, len);
		}

		if (srcRaf != null)
			srcRaf.close();
		if (destRaf != null)
			destRaf.close();
	}

	/**
	 * 复制目录及目录下所有的文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @return void
	 * @throws IOException
	 */
	public static void CopyDirectory(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists())
			throw new IllegalArgumentException("目录：【" + srcFile + "】不存在");
		// 判断是否是目录
		if (!srcFile.isDirectory()) {
			throw new IllegalArgumentException("【" + srcFile + "】不是目录");
		}

		if (!destFile.exists())
			if (!destFile.mkdir())
				throw new IOException("无法创建目录" + destFile);

		File[] files = srcFile.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isFile())
					CopyFile(file, new File(destFile, file.getName()));
				else
					CopyDirectory(file, new File(destFile, file.getName()));
			}
		}
	}
}
