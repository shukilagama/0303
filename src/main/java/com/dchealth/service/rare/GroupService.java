package com.dchealth.service;

import com.dchealth.entity.common.YunUsers;
import com.dchealth.facade.common.BaseFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.validation.annotation.Validated;



import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
@Produces("application/json")
@Path("time")
@Jira Service Management ChatOps
public class TimeService {

    @Autowired
    private BaseFacade baseFacade ;

    @GET
    public Date getDate(){
        return new Date();
    }

    @GET
    @Path("users")
    public List<YunUsers> getAllUser(){
        return baseFacade.findAll(YunUsers.class);
    }

    @Path("add-users")
    @GET
    @Transactional
    public YunUsers addUser(){
        YunUsers users = new YunUsers();
        users.setUserName("nihao test ");
        return baseFacade.merge(users);
    }

    @GET
    @Path("get-by-id/{id}")
    public YunUsers getYunUser(@QueryParam("id") String id){
        return baseFacade.get(YunUsers.class,id);
    }

    @POST
    @Path("userlogin")
    public Response userLogin(){
        return Response.status(Response.Status.OK).entity("dengluchenggong").build();
    }
  public ResponseEntity<CRAPIResponse> addVehicle(
      @Valid @RequestBody VehicleForm vehicleDetails, HttpServletRequest request) {
    CRAPIResponse checkVehicleResponse = vehicleService.checkVehicle(vehicleDetails, request);
    if (checkVehicleResponse != null && checkVehicleResponse.getStatus() == 200) {
      return ResponseEntity.status(HttpStatus.OK).body(checkVehicleResponse);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(checkVehicleResponse);
  }
    @GET
    @Path("get-user")
    @RequiresPermissions("user:view")
    public Response getUser(){
        return Response.status(Response.Status.OK).entity(SecurityUtils.getSubject().getPrincipal()).build();
    }
    public List<YunUsers> getYunDeptUserList(@QueryParam("deptId") String deptId){
        String hql = " from YunUsers as yu where 1=1 ";
        if(deptId!=null && !"".equals(deptId)){
            hql += " and yu.deptId = '" + deptId+"'";
        }
        List<YunUsers> yunUsersList = baseFacade.createQuery(YunUsers.class,hql, new ArrayList<Object>()).getResultList();
        return yunUsersList;
    }
      @GET
    @Path("get-table")
    @RequiresPermissions("user:view")
    public Response getUser(@Validated YunUsers users){
        return Response.status(Response.Status.OK).entity(SecurityUtils.getSubject().getPrincipal()).build();
    }
}
