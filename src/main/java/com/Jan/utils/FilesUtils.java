package com.Jan.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * Created by hsx on 2017/3/5.
 */
@Transactional
@Controller
@RequestMapping("/file_utils")
public class FilesUtils {
	// 实现单个文件上传
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadfile(@RequestParam("fileUpLoad") MultipartFile file, HttpServletRequest req)
			throws IOException {
		// 先判断存储文件的文件夹是否存在,若不存在,新建一个
		File targetFile = new File(req.getSession().getServletContext().getRealPath("/upload"));
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}
		// 将文件存到指定的目录下,并命名为uuid+file.getOriginalFilename();
		File f;
		file.transferTo(
				f = new File(targetFile.getPath() + "/" + UUID.randomUUID().toString() + file.getOriginalFilename()));
		return f.getPath();
	}

	@ResponseBody
	@RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
	public String uploadfiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest req) throws IOException {
		// 先判断存储文件的文件夹是否存在,若不存在,新建一个
		File targetFile = new File(req.getSession().getServletContext().getRealPath("/upload"));
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}
		// 将文件存到指定的目录下,并命名为uuid+file.getOriginalFilename();
		List<String> list = new ArrayList<String>();
		File single;
		for (int t = 0; t < files.length; t++) {
			single = new File(
					targetFile.getPath() + "/" + UUID.randomUUID().toString() + files[t].getOriginalFilename());
			files[t].transferTo(single);
			list.add(single.getPath());
		}
		return JSON.toJSONString(list);
	}

	@ResponseBody
	@RequestMapping(value = "/uploaCompressPic", method = RequestMethod.POST)
	public String uploaCompressPic(@RequestParam("pictures") MultipartFile[] files, HttpServletRequest req)
			throws IOException {
		// 先判断存储文件的文件夹是否存在,若不存在,新建一个
		File targetFile = new File(req.getSession().getServletContext().getRealPath("/upload"));
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}

		File outFile = new File(req.getSession().getServletContext().getRealPath("/compress"));
		if (!outFile.exists()) {
			outFile.mkdir();
		}

		// 将文件存到指定的目录下,并命名为uuid+file.getOriginalFilename();
		@SuppressWarnings("rawtypes")
		List<Map> list = new ArrayList<Map>();
		File single;
		for (int t = 0; t < files.length; t++) {

			Map<String, String> map = new HashMap<String, String>();
			single = new File(
					targetFile.getPath() + "/" + UUID.randomUUID().toString() + files[t].getOriginalFilename());

			BufferedImage img = ImageIO.read(files[t].getInputStream());
			if (img != null) {
				int width = img.getWidth();
				int height = img.getHeight();

				if (img.getWidth() > 200 || img.getHeight() > 200) {
					width = 200;
					height = (int) (200 * (img.getHeight() * 1.0 / img.getWidth()));
				}
				System.out.println("-------------------------------");
				System.out.println(img.getType());
				System.out.println("-------------------------------");
				BufferedImage bfi = new BufferedImage(width, height, img.getType());
				bfi.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
				File fff = new File(req.getSession().getServletContext().getRealPath("/compress") + "/"
						+ UUID.randomUUID().toString() + files[t].getOriginalFilename());
				ImageIO.write(bfi, FilenameUtils.getExtension(files[t].getOriginalFilename()), fff);
				map.put("present", fff.getPath());

				files[t].transferTo(single);
				map.put("origin", single.getPath());

				list.add(map);
			}
		}
		return JSON.toJSONString(list);
	}

	// @ResponseBody
	// @RequestMapping(value = "/upload_stream", method = RequestMethod.POST)
	// public String uploadFilesStream(String imgString, HttpServletRequest req)
	// {
	// SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HHmmss");
	// String timeString = "";
	// System.out.println(timeString = format.format(new Date()));
	// File file = new
	// File(req.getSession().getServletContext().getRealPath("/upload"));
	// if (!file.exists()) {
	// file.mkdirs();
	// }
	//
	// try {
	// // ctrl + alt + T 自动生成try catch 模块
	// System.out.println(imgString.indexOf(","));
	// byte[] bytes = new
	// BASE64Decoder().decodeBuffer(imgString.substring(imgString.indexOf(",") +
	// 1));
	// OutputStream out = new FileOutputStream(new File(file, new
	// Date().getTime() + ".png"));
	// for (int t = 0; t < bytes.length; t++) {
	// if (bytes[t] < 0) {
	// bytes[t] += 256;
	// }
	// }
	// out.write(bytes);
	// } catch (IOException e) {
	// e.printStackTrace();
	// return "faild";
	// }
	// return "success";
	// }

	/**
	 * @param files
	 * @param req
	 *            用户获取ServletContext
	 * @return 返回一个包括文件存储路径的List<Map> ,map(present,origin)
	 *         present代表压缩过的图片路径,origin 表示原始图片的路径
	 * @throws IOException
	 */
	public static List<Map<String, String>> uploaCompressPicUtils(List<MultipartFile> files, HttpServletRequest req)
			throws IOException {
		// 先判断存储文件的文件夹是否存在,若不存在,新建一个
		File targetFile = new File(req.getSession().getServletContext().getRealPath("/upload"));
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}

		File outFile = new File(req.getSession().getServletContext().getRealPath("/compress"));
		if (!outFile.exists()) {
			outFile.mkdir();
		}

		// 将文件存到指定的目录下,并命名为uuid+file.getOriginalFilename();
		@SuppressWarnings("rawtypes")
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		File single;
		for (int t = 0; t < files.size(); t++) {

			Map<String, String> map = new HashMap<String, String>();
			single = new File(targetFile.getPath() + File.separator + UUID.randomUUID().toString()
					+ files.get(t).getOriginalFilename());

			BufferedImage img = ImageIO.read(files.get(t).getInputStream());
			if (img != null) {
				int width = img.getWidth();
				int height = img.getHeight();

				if (img.getWidth() > 200 || img.getHeight() > 200) {
					width = 200;
					height = (int) (200 * (img.getHeight() * 1.0 / img.getWidth()));
				}
				BufferedImage bfi = new BufferedImage(width, height, img.getType());
				bfi.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
				File fff = new File(req.getSession().getServletContext().getRealPath(File.separator + "compress")
						+ File.separator + UUID.randomUUID().toString() + files.get(t).getOriginalFilename());
				ImageIO.write(bfi, FilenameUtils.getExtension(files.get(t).getOriginalFilename()), fff);
				map.put("present", fff.getPath());
				files.get(t).transferTo(single);
				map.put("origin", single.getPath());
				list.add(map);
			}
		}
		return list;
	}
}
