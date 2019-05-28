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
 * @Description: 用户资源关系模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_role_resource")
public class SysRoleResourceModel {

	/**
	 * 主键
	 */
	    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	private Long id;

	/**
	 * 角色id
	 */
	    @Column(name = "role_id")
	private Long roleId;

	/**
	 * 资源id
	 */
	    @Column(name = "resource_id")
	private Long resourceId;
}
