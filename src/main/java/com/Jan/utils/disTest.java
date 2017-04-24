package com.Jan.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dis")
public class disTest {
	@ResponseBody
	@RequestMapping(value = "/t", method = RequestMethod.GET)
	public String t() {
		return "test";
	}

	@ResponseBody
	@RequestMapping(value = "/t1/{name}", method = RequestMethod.GET)
	public String distest1(@PathVariable String name) {
		return "hello " + name;
	}

	@ResponseBody
	@RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
	public String findPet(@MatrixVariable(value = "q", pathVar = "ownerId") int q1,
			@MatrixVariable(value = "q", pathVar = "petId") int q2) {
		System.out.println(q1 + "---" + q2);
		return "@MatrixVariable--->success";
	}

	// 实现单个文件上传
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadfile(@RequestParam("fileUpLoad") MultipartFile file) throws IOException {

		String name = file.getOriginalFilename();
		// if (!file.isEmpty()) {
		// InputStream is = file.getInputStream();
		// int s;
		// FileOutputStream fos = new FileOutputStream(new File("D:/mk/" +
		// name));
		// byte by[] = new byte[1024];
		// while ((s = is.read(by)) != -1) {
		// // for (int i = 0; i < s; i++) {
		// fos.write(by, 0, s);
		// // }
		// }
		// is.close();
		//
		// fos.close();
		// }
		file.transferTo(new File("D:/mk/" + name));
		return "upload success";
	}

	// 实现多个文件上传
	@ResponseBody
	@RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
	public String uploadfiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest req) throws IOException {
		InputStream is = null;
		FileOutputStream fos = null;
		// 获得项目的绝对路径,如果该路径下的/upload不存在,就创建该目录
		File file = new File(req.getSession().getServletContext().getRealPath("/upload"));
		if (!file.exists()) {
			file.mkdirs();
		}
		for (MultipartFile mfile : files) {
			String name = mfile.getOriginalFilename();
			// is = mfile.getInputStream();
			// fos = new FileOutputStream("D:/mk/" + name);
			// int s;
			// byte[] bytes = new byte[1024];
			// while ((s = is.read(bytes)) != -1) {
			// fos.write(bytes, 0, s);
			// }
			System.out.println(file.getPath());
			mfile.transferTo(new File(file.getPath() + "/" + name));
		}
		// is.close();
		// fos.close();
		return "upload files success";
	}

	// @ResponseBody
	@RequestMapping(value = "download", method = RequestMethod.POST)
	public ResponseEntity<byte[]> download(@RequestParam String path) throws IOException {
		// FileInputStream fis = new FileInputStream(new File("D:/mk/" + path));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("test", path);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File("D:/mk/" + path)), headers,
				HttpStatus.CREATED);
	}

	@ResponseBody
	@RequestMapping(value = "tldtest", method = RequestMethod.POST)
	public String tldTest() {

		return "success";
	}
}
