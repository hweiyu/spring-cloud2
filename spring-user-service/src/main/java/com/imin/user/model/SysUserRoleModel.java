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
 * @Description: 用户角色关系模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_user_role")
public class SysUserRoleModel {

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
	 * 角色id
	 */
	    @Column(name = "role_id")
	private Long roleId;
}
