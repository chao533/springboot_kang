package com.szdtoo.common.filter;


//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.PathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.szdtoo.common.msg.ErrorCode;
//import com.szdtoo.common.msg.Message;
//import com.szdtoo.common.utils.JwtUtil;
//import com.szdtoo.common.utils.ResponseUtils;


/**
 * <p>Title: JwtAuthenticationFilter</p>  
 * <p>Description: token验证</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//	
//	private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//	
//    private static final PathMatcher pathMatcher = new AntPathMatcher();
//    //private String protectUrlPattern = "/user/**";
//    
//    private List<String> protectUrlPatternList = new ArrayList<String>();
//
//    public JwtAuthenticationFilter() {
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
//    	try {
//	        if (isProtectedUrl(request)) {
//	            Map<String, Object> claims = JwtUtil.validateTokenAndGetClaims(request);
//	            String role = String.valueOf(claims.get(JwtUtil.ROLE));
//	            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, Arrays.asList(() -> role)));
//	        }
//			filterChain.doFilter(request, response);
//		} catch (Exception e) {
//			ResponseUtils.responseResult(response, new Message<String>(ErrorCode.ERROR,"认证失败"));
//			return;
//		}
//    }
//
//    private boolean isProtectedUrl(HttpServletRequest request) {
//    	protectUrlPatternList.add("/user/**"); // 该请求需要带token验证
//    	//protectUrlPatternList.add("/test/**");
//    	for(String s : protectUrlPatternList) {
//    		if(pathMatcher.match(s, request.getServletPath())) {
//    			return true;
//    		}
//    	}
//    	return false;
//    	
////        return pathMatcher.match(protectUrlPattern, request.getServletPath());
//    }
//
//
//}
