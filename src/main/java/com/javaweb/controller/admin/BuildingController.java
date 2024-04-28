package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.AbstractDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import com.javaweb.utils.ReadFileUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ReadFileUtils readFileUtils;
    @RequestMapping(value="/admin/building-list", method= RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/building/list");
        String url=request.toString();
        DisplayTagUtils.of(request,buildingSearchRequest);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId=SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
        }
        List<BuildingSearchResponse> responseList= buildingService.findDividedAll(buildingSearchRequest,PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        buildingSearchRequest.setListResult(responseList);
        buildingSearchRequest.setTotalItems(buildingService.countTotalItems(buildingSearchRequest));
        mav.addObject(SystemConstant.MODEL,buildingSearchRequest);


        mav.addObject("modelSearch",buildingSearchRequest);
        mav.addObject("staffList",userService.getStaffs());
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }
    @RequestMapping(value="/admin/building-edit", method= RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO,HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/building/edit");
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }

    @RequestMapping(value="/admin/building-edit-{id}", method= RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(id);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        mav.addObject("buildingEdit",buildingDTO);
        return mav;
    }
    @RequestMapping(value="/repository/building-{imageName}", method= RequestMethod.GET)
    public void gettingImage(@PathVariable("imageName") String imageName,HttpServletRequest request, HttpServletResponse response)throws Exception{
        readFileUtils.doGet(request,response);
    }
}
