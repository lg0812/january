package com.Jan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.Timer.TokenListener;
import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.model.Attend;
import com.Jan.model.Messions;
import com.Jan.model.Share;
import com.alibaba.fastjson.JSON;

@Controller
@Transactional
@RequestMapping("/mc")
public class MessionController {
	@Resource
	public SessionFactory sessionFactory;
	final Logger log = LogManager.getLogger(TokenListener.class);
	// @Autowired
	// public MessionService messionService;
	// @Autowired
	// public void setMessionService(MessionService messionService) {
	// this.messionService = messionService;
	// }

	@RequestMapping("/create_mession")
	@ResponseBody
	public String createMession(Messions mession) {
		BaseResp baseResp = new BaseResp();
		if (!StringUtils.isEmpty(mession) && !StringUtils.isEmpty(mession.mission_href)
				&& !StringUtils.isEmpty(mession.title)) {
			// messionService.createMession(mession);
			baseResp.setCode(ApiConsts.OK);
			baseResp.setMessage("insert success!");
		} else {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setMessage("insert failed!");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/share_mession")
	@ResponseBody
	public String shareMession(Share share) {
		BaseResp baseResp = new BaseResp();
		if (!StringUtils.isEmpty(share) && !StringUtils.isEmpty(share.messionId)
				&& !StringUtils.isEmpty(share.sharedId)) {
			share.leader = true;
			this.sessionFactory.getCurrentSession().save(share);
			baseResp.setCode(ApiConsts.OK);
			baseResp.setMessage("insert success!");
		} else {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setMessage("insert failed!");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/click_share")
	@ResponseBody
	public String clickMession(Share share) {
		BaseResp baseResp = new BaseResp();
		if (!StringUtils.isEmpty(share) && !StringUtils.isEmpty(share.messionId)
				&& !StringUtils.isEmpty(share.openId)) {
			List<Share> list = this.sessionFactory.getCurrentSession()
					.createQuery("from Share where messionId = " + share.messionId + "and openId = " + share.openId)
					.list();
			if (list.size() == 0) {
				this.sessionFactory.getCurrentSession().save(share);
			}
			baseResp.setCode(ApiConsts.OK);
			baseResp.setMessage("update success!");
		} else {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setMessage("update failed!");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/get_messions")
	@ResponseBody
	public String getMession() {
		BaseResp baseResp = new BaseResp();
		List<Share> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Messions order by insert_time desc").list();
		baseResp.setResult(list);
		baseResp.setCode(ApiConsts.OK);
		baseResp.setMessage("get success!");
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/getMessionById")
	@ResponseBody
	public String getMessionById(long messionId) {
		BaseResp baseResp = new BaseResp();
		List<Share> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Messions where messionId = " + messionId).list();
		baseResp.setResult(list);
		baseResp.setCode(ApiConsts.OK);
		baseResp.setMessage("get success!");
		log.info(JSON.toJSONString(list));
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/getShares")
	@ResponseBody
	public String getShares(long messionId) {
		BaseResp baseResp = new BaseResp();
		List<Share> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Share where messionId = " + messionId).list();

		List<Messions> listmessions = this.sessionFactory.getCurrentSession()
				.createQuery("from Messions where messionId = " + messionId).list();
		List listr = new ArrayList<Object>();
		listr.add(list);
		listr.add(listmessions.get(0).mession_times);

		baseResp.setResult(listr);
		baseResp.setCode(ApiConsts.OK);
		baseResp.setMessage("get success!");
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/getAttends")
	@ResponseBody
	public String getAttends(long messionId) {
		BaseResp baseResp = new BaseResp();
		List<Share> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Attend where messionId = " + messionId).list();
		baseResp.setResult(list);
		baseResp.setCode(ApiConsts.OK);
		baseResp.setMessage("get success!");
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/attend")
	@ResponseBody
	public String attend(Attend at) {
		BaseResp baseResp = new BaseResp();
		if (!StringUtils.isEmpty(at) && !StringUtils.isEmpty(at.messionId) && !StringUtils.isEmpty(at.openId)) {
			List<Attend> list = this.sessionFactory.getCurrentSession()
					.createQuery("from Attend where messionId = " + at.messionId + " and openId = '" + at.openId + "'")
					.list();
			if (list.size() == 0) {
				this.sessionFactory.getCurrentSession().save(at);
			}
			baseResp.setCode(ApiConsts.OK);
			baseResp.setMessage("get success!");
		} else {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setMessage("failed success!");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/test_mession")
	@ResponseBody
	public String test_mession(Messions mession) {
		if (mession.openId != null) {
			System.out.println(JSON.toJSONString(mession));
			return JSON.toJSONString(mession);
		}
		return JSON.toJSONString("failed");
	}

	@ResponseBody
	@RequestMapping(value = "/test_success")
	public String testSuccess(String str) {
		System.out.print("--------------");
		return "success";
	}
}
