package com.imin.adminweb.model.agent;


import lombok.*;

import javax.persistence.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 设备部署信息模型
 * @date 2019-02-22 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_device_deploy")
public class DeviceDeployModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /**
     * 设备型号
     */
    @Column(name = "model")
    private String model;

    /**
     * SN
     */
    @Column(name = "sn")
    private String sn;

    /**
     * 出货日期
     */
    @Column(name = "shipping_date")
    private String shippingDate;

    /**
     * 激活日期
     */
    @Column(name = "activation_date")
    private String activationDate;

    /**
     * 累计使用时间
     */
    @Column(name = "cumulative_usetime")
    private String cumulativeUsetime;

}
