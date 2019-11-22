package com.spring.development.common;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.utils
 * @Author xuzhenkui
 * @Date 2019/9/19 9:35
 */
public enum ResultCode {

//    信息，服务器收到请求，需要请求者继续执行操作
    CONTINUE(100,"继续请求"),
    SWITCHING_PROTOCOLS(101,"切换协议"),

//    成功，操作被成功接收并处理
    SUCCESS(200,"请求成功"),
    CREATED(201,"资源已被创建"),
    ACCEPTED(202,"已经接受请求，但未处理完成"),
    NON_AUTHORITATIVE_INFORMATION(203,"请求成功. 但返回的meta信息不在原始的服务器"),
    NO_CONTENT(204,"服务器成功处理, 但未返回内容"),
    RESET_CONTENT(205,"服务器处理成功, 用户终端应重置文档视图"),
    PARTIAL_CONTENT(206,"服务器成功处理了部分GET请求"),

//    重定向，需要进一步的操作以完成请求
    MULTIPLE_CHOICES(300,"请求的资源可包括多个位置, 相应可返回一个资源特征与地址的列表用于用户终端选择"),
    MOVED_PERMANENTLY(301,"请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI.今后任何新的请求都应使用新的URI代替"),
    FOUND(302,"资源临时被移动. 客户端应继续使用原有URI"),
    SEE_OTHER(303,"查看其它地址"),
    NOT_MODIFIED(304,"所请求的资源未修改, 服务器返回此状态码时, 不会返回任何资源"),
    USE_PROXY(305,"所请求的资源必须通过代理访问"),
    UNUSED(306,"已经被废弃的HTTP状态码"),
    TEMPORARY_REDIRECT(307,"临时重定向"),

//    客户端错误，请求包含语法错误或无法完成请求
    BAD_REQUEST(400,"客户端请求的语法错误"),
    UNAUTHORIZED(401,"请求要求用户的身份认证"),
    FORBIDDEN(403,"服务器理解请求客户端的请求, 但是拒绝执行此请求"),
    NOT_FOUND(404,"服务器无法根据客户端的请求找到资源"),
    METHOD_NOT_ALLOWED(405,"客户端请求中的方法被禁止"),
    NOT_ACCEPTABLE(406,"服务器无法根据客户端请求的内容特性完成请求"),
    TIME_OUT(408,"服务器等待客户端发送的请求时间过长, 超时"),
    CONFLICT(409,"服务器处理请求时发生了冲突"),
    GONE(410,"客户端请求的资源已经不存在"),
    LENGTH_REQUIRED(411,"服务器无法处理客户端发送的不带Content-Length的请求信息"),
    PRECONDITION_FAILED(412,"客户端请求信息的先决条件错误"),
    REQUEST_ENTITY_TOO_LARGE(413,"请求的实体过大, 服务器无法处理,拒绝请求"),
    REQUEST_URI_TOO_LARGE(414,"请求的URI过长, 服务器无法处理"),
    UNSUPPORTED_MEDIA_TYPE(415,"服务器无法处理请求附带的媒体格式"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416,"客户端请求的范围无效"),
    EXPECTATION_FAILED(417,"服务器无法满足Expect的请求头信息"),

//    服务器错误，服务器在处理请求的过程中发生了错误
    INTERNAL_SERVER_ERROR(500,"服务器内部错误，无法完成请求"),
    NOT_IMPLEMENTED(501,"服务器不支持请求的功能，无法完成请求"),
    BAD_GATEWAY(502,"作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应"),
    SERVICE_UNAVAILABLE(503,"由于超载或系统维护，服务器暂时的无法处理客户端的请求"),
    GATEWAY_TIME_OUT(504,"充当网关或代理的服务器，未及时从远端服务器获取请求"),
    HTTP_VERSION_NOT_SUPPORTED(505,"服务器不支持请求的HTTP协议的版本，无法完成处理");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
