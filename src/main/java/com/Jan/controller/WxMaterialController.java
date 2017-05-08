package com.Jan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.model.UserToken;
import com.Jan.service.WxService;
import com.Jan.wx.RequestUtils;
import com.alibaba.fastjson.JSON;

@Controller
@Transactional
@RequestMapping("/material")
public class WxMaterialController {
	public static String ACCESS_TOKEN = "qj_900LNPtpApNZTJJlgKFUFJMmJUYBQNYmfxWTHm_SFuQiDNbJVhZsp3eI3sw96tPO1qiTikLfe3zeKfFJ6I0f2amfR2Zwkrqc3_XkI5okPMVeAFAWTC";
	@Autowired
	public WxService wxService;
	@Resource
	public SessionFactory sessionFactory;

	// 上传临时素材
	@ResponseBody
	@RequestMapping(value = "/upload_temp")
	public BaseResp upload(@RequestParam("file") MultipartFile file, String type, HttpServletRequest req) {
		BaseResp baseResp = new BaseResp();
		if (file == null) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("file is null");
		} else {

			UserToken token = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
					.uniqueResult();
			baseResp.setResult(wxService.wx_upload(token.getAccessToken(), type, save_file(file, req)));
			baseResp.setCode(ApiConsts.OK);
		}
		return baseResp;
	}

	// 获取临时素材
	// @Test
	@ResponseBody
	@RequestMapping(value = "/get_media_temp")
	public BaseResp get_media_temp(String mediaId) {
		BaseResp baseResp = new BaseResp();
		if (mediaId == null) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("mediaId is null");
		} else {
			UserToken token = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
					.uniqueResult();
			baseResp.setResult(wxService.media_temp(token.getAccessToken(), mediaId));
			baseResp.setCode(ApiConsts.OK);
		}
		return baseResp;
	}

	// 上传永久素材
	@ResponseBody
	@RequestMapping(value = "/upload_long")
	public BaseResp upload_long(@RequestParam("file") MultipartFile file, String type, HttpServletRequest req)
			throws IOException {
		BaseResp baseResp = new BaseResp();
		if (file == null || type == null) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("file or type is null");
		} else {
			UserToken token = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
					.uniqueResult();
			baseResp.setResult(wxService.wx_upload_long(token.getAccessToken(), type, save_file(file, req)));
			baseResp.setCode(ApiConsts.OK);
		}
		return baseResp;
	}

	// 获取永久素材 通过mediaId
	@ResponseBody
	@RequestMapping("/get_material")
	public BaseResp get_material(String mediaId) {

		BaseResp baseResp = new BaseResp();
		if (mediaId == null) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("mediaId is null");
		} else {
			UserToken token = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
					.uniqueResult();
			baseResp.setResult(wxService.media_long(token.getAccessToken(), mediaId));
			baseResp.setCode(ApiConsts.OK);
		}
		return baseResp;
	}

	@Test
	// 获取素材总数
	@ResponseBody
	@RequestMapping("/get_total")
	public BaseResp get_total() {
		BaseResp baseResp = new BaseResp();
		UserToken token = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken").uniqueResult();
		baseResp.setResult(wxService.get_media_total(token.getAccessToken()));
		baseResp.setCode(ApiConsts.OK);
		return baseResp;
	}

	// 获取素材列表
	@Test
	@ResponseBody
	@RequestMapping("/get_list")
	public BaseResp get_list() {
		BaseResp baseResp = new BaseResp();
		UserToken token = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken").uniqueResult();
		baseResp.setResult(wxService.get_media_list(token.getAccessToken()));
		baseResp.setCode(ApiConsts.OK);
		return baseResp;
	}
	/*
	 * 
	 * 
	 * public static String ACCESS_TOKEN =
	 * "qj_900LNPtpApNZTJJlgKFUFJMmJUYBQNYmfxWTHm_SFuQiDNbJVhZsp3eI3sw96tPO1qiTikLfe3zeKfFJ6I0f2amfR2Zwkrqc3_XkI5okPMVeAFAWTC";
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/get_material_list") public BaseResp get_material_list()
	 * { get_list(); return null;
	 * 
	 * }
	 * 
	 * @Test public void get_total() { Map map = new HashMap<String, String>();
	 * map.put("access_token", ACCESS_TOKEN); String result =
	 * RequestUtils.getRequest(
	 * "https://api.weixin.qq.com/cgi-bin/material/get_materialcount", map); }
	 * 
	 * @Test public void getByMediaId() { Map map = new HashMap<String,
	 * String>(); map.put("access_token", ACCESS_TOKEN); map.put("media_id",
	 * "79Dc9zX7gYj0VamVztpEsssBxc2jc_8USiWTDSJrb8nNraKhBSKcikFxGJVG_S4w");
	 * String result =
	 * RequestUtils.getRequest("https://api.weixin.qq.com/cgi-bin/media/get",
	 * map);
	 * 
	 * }
	 * 
	 * @Test public void get_list() { Map map = new HashMap<String, String>();
	 * Map map1 = new HashMap<String, String>();
	 * 
	 * map.put("access_token", ACCESS_TOKEN); map1.put("type", "image");
	 * map1.put("offset", "0"); map1.put("count", "20");
	 * RequestUtils.postRequest(
	 * "https://api.weixin.qq.com/cgi-bin/material/batchget_material", map,
	 * JSON.toJSONString(map1)); }
	 * 
	 * @Test public void upload() { Map map = new HashMap<String, String>();
	 * map.put("access_token", ACCESS_TOKEN); try { HttpEntity entity =
	 * MultipartEntityBuilder.create().addBinaryBody("media", new
	 * File("D:/dongman.jpg")) .build(); Request post = Request.Post(
	 * "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" +
	 * ACCESS_TOKEN + "&type=image"); post.body(entity);
	 * 
	 * System.out.println(EntityUtils.toString(post.execute().returnResponse().
	 * getEntity())); } catch (FileNotFoundException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * @Test public void upload_long() { Map map = new HashMap<String,
	 * String>(); map.put("access_token", ACCESS_TOKEN); try { HttpEntity entity
	 * = MultipartEntityBuilder.create().addBinaryBody("media", new
	 * File("D:/dongman.jpg")).addTextBody("type", "image") .build(); Request
	 * post = Request.Post(
	 * "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" +
	 * ACCESS_TOKEN ); post.body(entity);
	 * 
	 * System.out.println(EntityUtils.toString(post.execute().returnResponse().
	 * getEntity())); } catch (FileNotFoundException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	@SuppressWarnings("null")
	public File save_file(MultipartFile file, HttpServletRequest req) {
		// 先判断存储文件的文件夹是否存在,若不存在,新建一个
		File targetFile = new File(req.getSession().getServletContext().getRealPath("/upload"));
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}

		// 将文件存到指定的目录下,并命名为uuid+file.getOriginalFilename();
		File f = null;
		try {
			file.transferTo(f = new File(
					targetFile.getPath() + "/" + UUID.randomUUID().toString() + file.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f != null ? f : null;
	}
}
