package com.zw.service.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.base.util.ThreadLocalHelper;
import com.base.util.TraceLoggerUtil;
import com.zw.service.dto.UserInfoDTO;

/**
 * <strong>Title : <br>
 * </strong> <strong>Description : </strong>@类注释说明写在此处@<br>
 * <strong>Create on : 2017年02月21日<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) zw.<br>
 * </strong>
 * <p>
 *
 * @author department:技术开发部 <br>
 *         username:zh-pc <br>
 *         email: <br>
 * @version <strong>zw有限公司-运营平台</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class AuthProviderInterceptor implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            String userInfo = RpcContext.getContext().getAttachment(ThreadLocalHelper.USER_INFO_DTO);
            UserInfoDTO userInfoDTO = JSON.toJavaObject(JSON.parseObject(userInfo), UserInfoDTO.class);
            ThreadLocalHelper.getMap().put(ThreadLocalHelper.USER_INFO_DTO, userInfoDTO);
        } catch (Exception ex) {
            TraceLoggerUtil.error("用户上下文信息序列化失败", ex);
        }
        return invoker.invoke(invocation);
    }

}
