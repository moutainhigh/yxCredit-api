package com.api.model.sortmsg;

import java.io.Serializable;

/**
 * 短信服务请求实体
 * @author 陈清玉
 */
public class MsgRequest implements Serializable {

    private static final long serialVersionUID = 5502226723331101223L;
    /**
     * 手机号码
     */
    private String  phone;
    /**
     * 短信内容（模板编号和短信内容不能都为空）
     */
    private String  content;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MsgRequest{" +
                "phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
