package com.pzj.core.imgsrv.engine;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.imgsrv.engine.exception.ImgSystemException;
import com.pzj.core.imgsrv.engine.properties.LdapPropertiesLoader;

@Component
public class LadpEngine {
	private static final Logger logger         = LoggerFactory.getLogger(LadpEngine.class);

    private static String       authentication = "simple";

    private  NamingEnumeration get(String userName, String password, String searchRoot, String filter) throws NamingException {
        DirContext ctx = null;
        try {
            ctx = link(userName, password);
            // 查询节点
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration en = ctx.search(searchRoot, filter, constraints); // 查询所有用户
            return en;
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    // ignore
                }
            }
        }

    }

    private  DirContext link(String allName, String password) throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LdapPropertiesLoader.getUrl());
        env.put(Context.SECURITY_AUTHENTICATION, authentication);
        env.put(Context.SECURITY_PRINCIPAL, allName);
        env.put(Context.SECURITY_CREDENTIALS, password);

        return new InitialDirContext(env);
    }

    public  boolean verify(String userName, String pwd) {
        String root = LdapPropertiesLoader.getBase();
        String firstName = LdapPropertiesLoader.getUserDn();
        String password = LdapPropertiesLoader.getPassword();

        NamingEnumeration<SearchResult> en = null;
        try {
            // 先通过用户名获取用户对象
            en = get(firstName + "," + root, password, root, "(uid=" + userName + ")");
            if (en != null && en.hasMoreElements()) {
                SearchResult result = null;
                result = en.next();
                if (en.hasMoreElements()) {// 用户名查到多个
                    return false;
                }
                DirContext dct = link(result.getNameInNamespace(), pwd);
                return dct != null ? true : false;
                
       
            }
        }  catch (javax.naming.AuthenticationException e) {
            return false;
        }  catch(NamingException e){
        	throw new ImgSystemException("解析连接认证失败",e);         
        } catch (Exception e) {
            throw new ImgSystemException("LDAP连接异常", e);
        }

        
        return false;
    }
}
