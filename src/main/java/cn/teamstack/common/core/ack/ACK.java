package cn.teamstack.common.core.ack;


import cn.teamstack.common.core.util.EnumUtils;

public enum ACK implements EnumUtils.CustomEnum<Integer> {
    SYSTEM_ERROR(999999, "系统错误"),
    USER_TOKEN_MISS(1, "token异常"),
    USER_NOT_EXIST(2, "用户不存在"),
    PASSWORD_ERROR(3, "密码不正确"),
    USERNAME_IS_EMPTY(4, "用户名为空"),
    PASSWORD_IS_EMPTY(5, "密码为空"),
    AUTH_CODE_IS_EMPTY(6, "验证码为空"),
    USER_ALREADY_LOGOUT(7, "用户已经登出"),
    USER_ALREADY_REGISTER(8, "用户已经注册"),
    EMAIL_FORMAT_ERROR(9, "邮箱格式不正确"),
    REQUEST_PARAMETER_MISS(10, "请求参数缺失"),
    ORIGIN_PASSWORD_ERROR(11, "原始密码不正确"),
    RESET_PASSWORD_ERROR(12, "重置密码异常"),
    TICKET_PASSWORD_ERROR(13, "重置密码异常"),
    OAUTH2_PLATFORM_NOT_EXIST(14, "三方认证平台不存在"),
    OAUTH2_PLATFORM_ENCODING_ERROR(15, "三方认证编码异常"),
    REGISTER_TOO_MUCH(16, "注册太频繁"),
    TICKET_REGISTER_ACTIVATE_INVALID(17, "激活链接已失效（已激活/超时）"),
    TICKET_REGISTER_ACTIVATE_ERROR(18, "激活认证失败，检查链接或重新注册"),
    TICKET_BINDING_ACTIVATE_ERROR(19, "绑定激活链接已失效"),
    EMAIL_ALREADY_EXIST(20, "邮箱已被占用"),


    USER_INFO_NOT_EXIST(25, "用户信息不存在"),
    USER_ACCOUNT_NOT_EXIST(26, "用户信息不存在"),

    WORKBENCH_EMPTY(30, "没有控制台"),
    PROJECT_EMPTY(31, "项目信息为空/已经被删除"),
    PROJECT_DELETE_ERROR_WITH_NAME(32, "删除项目出错（项目名错误）"),
    PROJECT_NO_RIGHT(33, "没有操作项目权限"),

    PROJECT_TEAM_NO_RIGHT(40, "没有项目成员管理权限"),
    ALREADY_INVITE_MEMBER(41, "已经邀请过该成员"),


    PROJECT_FOLDER_NOT_EXIST(50, "目录不存在"),
    PROJECT_FOLDER_ID_ERROR(51, "目录id非法"),
    PROJECT_FOLDER_ID_EXIST(52, "目录id已经存在"),
    PROJECT_FOLDERS_NOT_EXCEPTION(53, "目录树异常"),
    API_NOT_EXIST(70, "API文档不存在"),
    CREATE_PROJECT_NAME_IS_EMPTY(71, "API文档不存在"),
    EDIT_API_ERROR(72, "编辑API文档异常"),

    SEARCH_EMPTY(90, "搜索结果为空");


    private Integer value;
    public String msg;

    ACK(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    @Override
    public Integer realVal() {
        return value;
    }

}
