package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.RoleExtService;
import com.rf.privjoy.myStock.dto.RoleDTO;
import com.rf.privjoy.myStock.impl.persistent.Role;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;

@RestController
@RequestMapping("/role")
public class RoleExtServiceImpl implements RoleExtService {
	
	private MyStockDataService dataService;
	private MyStockConversionService conversionService;

	@Override
	public RoleDTO getRole(Long roleId) {
		Role role = dataService.getRoleById(roleId);
		if (role == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with given ID is not found");
		}
		return conversionService.convertToDTO(role);
	}
	
	@Override
	public List<RoleDTO> getAllRoles() {
		List<Role> roles = dataService.getAllRoles();
		return conversionService.convertToRoleDTOs(roles);
	}

	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}
	
	/**
	 * @param conversionService the conversionService to set
	 */
	@Autowired
	public void setMyStockConversionService(MyStockConversionService conversionService) {
		this.conversionService = conversionService;
	}
	
}
