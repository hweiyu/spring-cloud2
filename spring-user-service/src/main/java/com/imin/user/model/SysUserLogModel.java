package com.imin.user.model;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_user_log")
public class SysUserLogModel {

	/**
	 * 主键
	 */
	    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	private Long id;

	/**
	 * 用户id
	 */
	    @Column(name = "user_id")
	private Long userId;

	/**
	 * 模块
	 */
	    @Column(name = "module")
	private Integer module;

	/**
	 * 操作类型
	 */
	    @Column(name = "operate_type")
	private Integer operateType;

	/**
	 * 请求接口
	 */
	    @Column(name = "request_url")
	private String requestUrl;

	/**
	 * ip
	 */
	    @Column(name = "ip")
	private String ip;

	/**
	 * 请求参数
	 */
	    @Column(name = "request_data")
	private String requestData;

	/**
	 * 返回参数
	 */
	    @Column(name = "response_data")
	private String responseData;

	/**
	 * 备注
	 */
	    @Column(name = "remark")
	private String remark;

	/**
	 * 日志记录时间
	 */
	    @Column(name = "log_time")
	private String logTime;
}
