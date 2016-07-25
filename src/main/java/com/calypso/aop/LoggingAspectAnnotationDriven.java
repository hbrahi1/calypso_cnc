/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.calypso.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Class used to house all aspect-related method.
 *
 * @author arnaldopiccinelli
 */
@Aspect
@EnableAspectJAutoProxy
@Component
@Configuration
public class LoggingAspectAnnotationDriven {

    private static Log logger = LogFactory.getLog(LoggingAspectAnnotationDriven.class);

    @Before("execution(* com.calypso.web.GeneralController.handleException(..))")
    public void beforeExceptionHandler(JoinPoint jp) {
        logger.info("(beforeExceptionHandler) Before executing '" + jp.getSignature().toLongString() + "'");
    }

    @After("execution(* com.calypso.web.GeneralController.handleException(..))")
    public void afterExceptionHandler(JoinPoint jp) {
        logger.info("(afterExceptionHandler) Before executing '" + jp.getSignature().toLongString() + "'");
    }

    @Before("execution(* com.calypso.config.MultiHttpSecurityConfig.*.*(..))")
    public void beforeMultiHttpSecurityConfigSubClasses(JoinPoint jp) {
        logger.info("(beforeMultiHttpSecurityConfigSubClasses) Before executing '" + jp.getSignature().toLongString() + "'");
    }

    @Before("execution(* com.calypso.config.MultiHttpSecurityConfig.*(..))")
    public void beforeMultiHttpSecurityConfig(JoinPoint jp) {
        logger.info("(beforeMultiHttpSecurityConfig) Before executing '" + jp.getSignature().toLongString() + "'");
    }

    @Before("execution(* com.calypso.SampleWebUiApplication.*(..))")
    public void beforeSampleWebUiApplication(JoinPoint jp) {
        logger.info("(beforeSampleWebUiApplication) Before executing '" + jp.getSignature().toLongString() + "'");
    }

    @After("execution(* com.calypso.SampleWebUiApplication.*(..))")
    public void afterSampleWebUiApplication(JoinPoint jp) {
        logger.info("(afterSampleWebUiApplication) After executing '" + jp.getSignature().toLongString() + "'");
    }

    @Before("execution(* com.calypso.service.ClinicServiceImpl.*(..))")
    public void beforeClinicServiceImpl(JoinPoint jp) {
        logger.info("(beforeClinicServiceImpl) Before executing '" + jp.getSignature().toLongString() + "'");
    }

    @After("execution(* com.calypso.service.ClinicServiceImpl.*(..))")
    public void afterClinicServiceImpl(JoinPoint jp) {
        logger.info("(afterClinicServiceImpl) After executing '" + jp.getSignature().toLongString() + "'");
    }

//    @Before("execution(* com.calypso.web.Pet*.*(..))")
//    public void beforePet(JoinPoint jp) {
//        logger.info("(beforePet) PET Before executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @After("execution(* com.calypso.web.Pet*.*(..))")
//    public void afterPet(JoinPoint jp) {
//        logger.info("(afterPet) PET After executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @Before("execution(* com.calypso.web.*.*(*))")
//    public void beforeAdviceWeb(JoinPoint jp) {
//        logger.info("(beforeAdviceWeb) Before executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @After("execution(* com.calypso.web.*.*(*))")
//    public void afterAdviceWeb(JoinPoint jp) {
//        logger.info("(afterAdviceWeb) After executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @Before("execution(* com.calypso.service.*.*(*))")
//    public void beforeAdviceService(JoinPoint jp) {
//        logger.info("(beforeAdviceService) Before executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @After("execution(* com.calypso.service.*.*(*))")
//    public void afterAdviceService(JoinPoint jp) {
//        logger.info("(afterAdviceService) After executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @Before("execution(* com.calypso.config.*.*(*))")
//    public void beforeAdviceConfig(JoinPoint jp) {
//        logger.info("(beforeAdviceConfig) Before executing '" + jp.getSignature().toLongString() + "'");
//    }
//
//    @After("execution(* com.calypso.config.*.*(*))")
//    public void afterAdviceConfig(JoinPoint jp) {
//        logger.info("(afterAdviceConfig) After executing '" + jp.getSignature().toLongString() + "'");
//    }

}
