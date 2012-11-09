/**
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onehippo.gogreen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * UserAgentUtils
 * @version $Id$
 */
public class UserAgentUtils {
    
    private static Pattern [] mobileUserAgentPatterns = 
    {
        Pattern.compile(".*(iphone|ipod).*", Pattern.CASE_INSENSITIVE),
        Pattern.compile(".*(android).*", Pattern.CASE_INSENSITIVE),
        Pattern.compile(".*(blackberry|opera mini).*", Pattern.CASE_INSENSITIVE),
        Pattern.compile(".*(webos|palm|treo).*", Pattern.CASE_INSENSITIVE),
        Pattern.compile(".*(kindle|pocket|o2|vodafone|wap|midp|psp).*", Pattern.CASE_INSENSITIVE)
    };
    
    public static boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if(StringUtils.isEmpty(userAgent)) {
            return false;
        }
        for (final Pattern pattern : mobileUserAgentPatterns) {
            final Matcher matcher = pattern.matcher(userAgent);
            if (matcher.find()) {
                return true;
            }
        }
        
        return false;
    }
    
}
