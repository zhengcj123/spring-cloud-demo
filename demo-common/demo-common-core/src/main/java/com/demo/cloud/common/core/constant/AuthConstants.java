package com.demo.cloud.common.core.constant;

/**
 * 认证常量类
 **/
public interface AuthConstants {


    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "bearer ";


    String AUTHENTICATION_SCHEME_BASIC = "Basic ";


    /**
     * Redis缓存权限规则key
     */
    String PERMISSION_ROLES_KEY = "auth:permission:roles";

    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    String CLIENT_DETAILS_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    String BASE_CLIENT_DETAILS_SQL = "select " + CLIENT_DETAILS_FIELDS + " from oauth_client_details";

    String FIND_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " order by client_id";

    String SELECT_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " where client_id = ?";


    /**
     * 密码加密方式
     */
    String BCRYPT = "{bcrypt}";

    String USER_ID_KEY = "user_id";

    String FACTORY_ID_KEY = "factory_id";
    /**
     * 请求来源
     */
    String FROM_SOURCE = "from-source";

    /**
     * 认证前缀
     */
    String AUTH_PREFIX="/oauth/token";
    /**
     * 内部请求
     */
    String INNER = "inner";
}
