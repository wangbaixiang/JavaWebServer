package com.jheto.webserver;

/***
 * This a base to create customs WebServers
 * 
 * @author Jheto Xekri
 * @license LGPL
 * @see https://github.com/JhetoX/JavaWebServer
 */
public class ResponseStatus {
    
    private ResponseStatus(){}
    
    public final static int CODE_CODE_ACCEPTED = 202;
    public final static String MESSAGE_ACCEPTED = "Accepted";
        
    public final static int CODE_BAD_GATEWAY = 502;
    public final static String MESSAGE_BAD_GATEWAY = "Bad Gateway";
    
    public final static int CODE_BAD_REQUEST = 400;
    public final static String MESSAGE_BAD_REQUEST = "Bad Request";
    
    public final static int CODE_CONFLICT = 409;
    public final static String MESSAGE_CONFLICT = "Conflict";
    
    public final static int CODE_CONTINUE = 100;
    public final static String MESSAGE_ = "Continue";
    
    public final static int CODE_CREATED = 201;
    public final static String MESSAGE_CREATED = "Created";
    
    public final static int CODE_EXPECTATION_FAILED = 417;
    public final static String MESSAGE_EXPECTATION_FAILED = "Expectation Failed";
    
    public final static int CODE_FAILED_DEPENDENCY = 424;
    public final static String MESSAGE_FAILED_DEPENDENCY = "Failed Dependency (WebDAV, RFC4918)";
    
    public final static int CODE_FORBIDDEN = 403;
    public final static String MESSAGE_FORBIDDEN = "Forbidden";
    
    public final static int CODE_FOUND = 302;
    public final static String MESSAGE_FOUND = "Found";
    
    public final static int CODE_GATEWAY_TIMEOUT = 504;
    public final static String MESSAGE_GATEWAY_TIMEOUT = "Gateway Timeout";
    
    public final static int CODE_GONE = 410;
    public final static String MESSAGE_GONE = "Gone";
    
    public final static int CODE_HTTP_VERSION_NOT_SUPPORTED = 505;
    public final static String MESSAGE_HTTP_VERSION_NOT_SUPPORTED = "HTTP Version Not Supported";
    
    public final static int CODE_INSUFFICIENT_STORAGE = 507;
    public final static String MESSAGE_INSUFFICIENT_STORAGE = "Insufficient Storage (WebDAV, RFC4918)";
    
    public final static int CODE_INTERNAL_SERVER_ERROR = 500;
    public final static String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    
    public final static int CODE_LENGTH_REQUIRED = 411;
    public final static String MESSAGE_LENGTH_REQUIRED = "Length Required";
    
    public final static int CODE_LOCKED = 423;
    public final static String MESSAGE_LOCKED = "Locked (WebDAV, RFC4918)";
    
    public final static int CODE_METHOD_NOT_ALLOWED = 405;
    public final static String MESSAGE_METHOD_NOT_ALLOWED = "Method Not Allowed";
    
    public final static int CODE_MOVED_PERMANENTLY = 301;
    public final static String MESSAGE_MOVED_PERMANENTLY = "Moved Permanently";
    
    public final static int CODE_MULTI_STATUS = 207;
    public final static String MESSAGE_MULTI_STATUS = "Multi-Status (WebDAV, RFC2518)";
    
    public final static int CODE_MULTIPLE_CHOICES = 300;
    public final static String MESSAGE_MULTIPLE_CHOICES = "Multiple Choices";
    
    public final static int CODE_NETWORK_AUTHENTICATION_REQUIRED = 511;
    public final static String MESSAGE_NETWORK_AUTHENTICATION_REQUIRED = "Network Authentication Required (RFC6585)";
    
    public final static int CODE_NO_CONTENT = 204;
    public final static String MESSAGE_NO_CONTENT = "No Content";
    
    public final static int CODE_NON_AUTHORITATIVE_INFORMATION = 203;
    public final static String MESSAGE_NON_AUTHORITATIVE_INFORMATION = "Non-Authoritative Information (since HTTP/1.1)";
    
    public final static int CODE_NOT_ACCEPTABLE = 406;
    public final static String MESSAGE_NOT_ACCEPTABLE = "Not Acceptable";
    
    public final static int CODE_NOT_EXTENDED = 510;
    public final static String MESSAGE_NOT_EXTENDED = "Not Extended (RFC2774)";
    
    public final static int CODE_NOT_FOUND = 404;
    public final static String MESSAGE_NOT_FOUND = "Not Found";
    
    public final static int CODE_NOT_IMPLEMENTED = 501;
    public final static String MESSAGE_NOT_IMPLEMENTED = "Not Implemented";
    
    public final static int CODE_NOT_MODIFIED = 304;
    public final static String MESSAGE_NOT_MODIFIED = "Not Modified";
    
    public final static int CODE_OK = 200;
    public final static String MESSAGE_OK = "OK";
    
    public final static int CODE_PARTIAL_CONTENT = 206;
    public final static String MESSAGE_PARTIAL_CONTENT = "Partial Content";
    
    public final static int CODE_PAYMENT_REQUIRED = 402;
    public final static String MESSAGE_PAYMENT_REQUIRED = "Payment Required";
    
    public final static int CODE_PRECONDITION_FAILED = 412;
    public final static String MESSAGE_PRECONDITION_FAILED = "Precondition Failed";
    
    public final static int CODE_PRECONDITION_REQUIRED = 428;
    public final static String MESSAGE_PRECONDITION_REQUIRED = "Precondition Required (RFC6585)";
    
    public final static int CODE_PROCESSING = 102;
    public final static String MESSAGE_PROCESSING = "Processing (WebDAV, RFC2518)";
    
    public final static int CODE_PROXY_AUTHENTICATION_REQUIRED = 407;
    public final static String MESSAGE_PROXY_AUTHENTICATION_REQUIRED = "Proxy Authentication Required";
    
    public final static int CODE_REQUEST_ENTITY_TOO_LARGE = 413;
    public final static String MESSAGE_REQUEST_ENTITY_TOO_LARGE = "Request Entity Too Large";
    
    public final static int CODE_REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
    public final static String MESSAGE_REQUEST_HEADER_FIELDS_TOO_LARGE = "Request Header Fields Too Large (RFC6585)";
    
    public final static int CODE_REQUEST_TIMEOUT = 408;
    public final static String MESSAGE_REQUEST_TIMEOUT = "Request Timeout";
    
    public final static int CODE_REQUEST_URI_TOO_LONG = 414;
    public final static String MESSAGE_REQUEST_URI_TOO_LONG = "Request-URI Too Long";
    
    public final static int CODE_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    public final static String MESSAGE_REQUESTED_RANGE_NOT_SATISFIABLE = "Requested Range Not Satisfiable";
    
    public final static int CODE_RESET_CONTENT = 205;
    public final static String MESSAGE_RESET_CONTENT = "Reset Content";
    
    public final static int CODE_SEE_OTHER = 303;
    public final static String MESSAGE_SEE_OTHER = "See Other (since HTTP/1.1)";
    
    public final static int CODE_SERVICE_UNAVAILABLE = 503;
    public final static String MESSAGE_SERVICE_UNAVAILABLE = "Service Unavailable";
    
    public final static int CODE_SWITCHING_PROTOCOLS = 101;
    public final static String MESSAGE_SWITCHING_PROTOCOLS = "Switching Protocols";
    
    public final static int CODE_TEMPORARY_REDIRECT = 307;
    public final static String MESSAGE_TEMPORARY_REDIRECT = "Temporary Redirect (since HTTP/1.1)";
    
    public final static int CODE_TOO_MANY_REQUESTS = 429;
    public final static String MESSAGE_TOO_MANY_REQUESTS = "Too Many Requests (RFC6585)";
    
    public final static int CODE_UNAUTHORIZED = 401;
    public final static String MESSAGE_UNAUTHORIZED = "Unauthorized";
    
    public final static int CODE_UNORDERED_COLLECTION = 425;
    public final static String MESSAGE_UNORDERED_COLLECTION = "Unordered Collection (WebDAV, RFC3648)";
    
    public final static int CODE_UNPROCESSABLE_ENTITY = 422;
    public final static String MESSAGE_UNPROCESSABLE_ENTITY = "Unprocessable Entity (WebDAV, RFC4918)";
    
    public final static int CODE_UNSUPPORTED_MEDIA_TYPE = 415;
    public final static String MESSAGE_UNSUPPORTED_MEDIA_TYPE = "Unsupported Media Type";
    
    public final static int CODE_UPGRADE_REQUIRED = 426;
    public final static String MESSAGE_UPGRADE_REQUIRED = "Upgrade Required (RFC2817)";
    
    public final static int CODE_USE_PROXY = 305;
    public final static String MESSAGE_USE_PROXY = "Use Proxy (since HTTP/1.1)";
    
    public final static int CODE_VARIANT_ALSO_NEGOTIATES = 506;
    public final static String MESSAGE_VARIANT_ALSO_NEGOTIATES = "Variant Also Negotiates (RFC2295)";
    
}
