package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    /**
     * Desc:
     * @param employeeDTO
     * @return
     * @author L_Misaki
     * @date 2023/9/19 15:17
     */
    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
    /**
     * Desc: 启用禁用员工
     * @param status
     * @param id
     * @return
     * @author L_Misaki
     */
    void startOrStop(Integer status, Long id);

    /**
     * Desc: 按照ID查询员工信息 
     * @param id
     * @return {@link com.sky.entity.Employee}
     * @author L_Misaki
     */
    Employee getById(Long id);

    /**
     * Desc: 更新员工信息
     * @param employeeDTO
     * @return
     * @author L_Misaki
     */
    void update(EmployeeDTO employeeDTO);
}
