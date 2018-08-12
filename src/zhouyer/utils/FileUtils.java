/**  

* <p>Title: FileUtils.java</p>  

* <p>Description: </p>  

* @author zhouyer  

* @date 2018��8��10��   

*/
package zhouyer.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * �ļ�����������
 * 
 * @author zhouyer
 *
 */
public class FileUtils {
	/**
	 * ɾ��ָ��Ŀ¼(�ļ���)����������������
	 * 
	 * @param dir
	 * @return void
	 * @throws IOException
	 */
	public static void DeleteDirectory(File dir) throws IOException {
		// �ж�Ŀ¼�Ƿ����
		if (!dir.exists()) {
			throw new IllegalArgumentException("Ŀ¼����" + dir + "��������");
		}

		// �ж��Ƿ���Ŀ¼
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("��" + dir + "�������ļ���");
		}

		// ��ȡĿ¼���������Ŀ¼���ļ�
		File[] files = dir.listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					DeleteDirectory(file);
				} else {
					System.out.print(file);
					if (!file.delete()) {
						throw new IOException("�޷�ɾ���ļ���" + file + "");
					} else {
						System.out.println(file + "�ļ�ɾ���ɹ�");
					}
				}
			}

		}

		System.out.println(dir);
		if (!dir.delete()) {
			throw new IOException("�޷�ɾ��Ŀ¼��" + dir);
		} else {
			System.out.println(dir + "�ļ���ɾ���ɹ�");
		}

	}

	/**
	 * �г���Ŀ¼���������Ŀ¼���ļ�
	 * 
	 * @param dir
	 * @return void
	 */
	public static void ListDirectory(File dir) {
		if (!dir.exists())
			throw new IllegalArgumentException("Ŀ¼����" + dir + "��������");
		if (!dir.isDirectory())
			throw new IllegalArgumentException("��" + dir + "������Ŀ¼");
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
	 * �г�ָ��Ŀ¼��ָ����չ���������ļ����û�����ָ�������չ�����û�Ҳ����ѡ���Ƿ��г� ��Ŀ¼�µ��ļ�
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
				if (file.isFile()) // ������ļ����ͼ���list��
					fileList.add(file);
				else {
					if (recursive) { // ѡ����Ŀ¼�µ��ļ�
						// �����Ŀ¼�еľ������˵������ļ���ӵ�list
						fileList.addAll(ListFile(file, ff, true));
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * �г�ָ��Ŀ¼��ָ����չ���������ļ����û�����ָ�������չ�����û�Ҳ����ѡ���Ƿ��г� ��Ŀ¼�µ��ļ�
	 * 
	 * @param dir
	 * @param extensions
	 * @param recursive
	 * @return
	 * @return List<File>
	 */
	public static List<File> ListFile(File dir, final String[] extensions, boolean recursive) {
		if (!dir.exists())
			throw new IllegalArgumentException("Ŀ¼����" + dir + "��������");
		// �ж��Ƿ���Ŀ¼
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("��" + dir + "�������ļ���");
		}

		// �ļ�������
		FileFilter ff = null;
		// ���û����չ�����Ͳ������˲���
		if (extensions == null || extensions.length == 0) {
			ff = new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					return true;// �൱�ڲ����й���
				}
			};
		} else {// ����չ�������й��˲���
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
	 * �����ļ�
	 * 
	 * @param srcFile
	 *            ���������ļ�
	 * @param destFile
	 *            �����ɹ����ļ�
	 * @throws IOException
	 * @return void
	 */
	public static void CopyFile(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists())
			throw new IllegalArgumentException("Ŀ¼����" + srcFile + "��������");
		// �ж��Ƿ���Ŀ¼
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException("��" + srcFile + "�������ļ�");
		}

		if (destFile.isDirectory())
			destFile = new File(destFile, srcFile.getName());

		// �ж�Ŀ���ļ��ļ��Ƿ���ڣ������ھʹ���
		if (!destFile.exists()) {
			if (!destFile.createNewFile()) {
				throw new IOException("�޷������ļ���" + destFile + "��");
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
	 * ����Ŀ¼��Ŀ¼�����е��ļ�
	 * 
	 * @param srcFile
	 * @param destFile
	 * @return void
	 * @throws IOException
	 */
	public static void CopyDirectory(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists())
			throw new IllegalArgumentException("Ŀ¼����" + srcFile + "��������");
		// �ж��Ƿ���Ŀ¼
		if (!srcFile.isDirectory()) {
			throw new IllegalArgumentException("��" + srcFile + "������Ŀ¼");
		}

		if (!destFile.exists())
			if (!destFile.mkdir())
				throw new IOException("�޷�����Ŀ¼" + destFile);

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
