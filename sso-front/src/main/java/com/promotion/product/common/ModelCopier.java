package com.promotion.product.common;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ModelCopier {
    public ModelCopier() {
    }

    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source != null && targetClass != null) {
            BeanCopier copier = BeanCopier.create(source.getClass(), targetClass, false);
            Object target = null;

            try {
                target = targetClass.newInstance();
                copier.copy(source, target, (Converter)null);
            } catch (Exception var5) {

            }

            return (T) target;
        } else {
            return null;
        }
    }

    public static <T> List<T> copyList(List<?> sourceList, Class<T> targetClass) {
        if (!CollectionUtils.isEmpty(sourceList) && targetClass != null) {
            BeanCopier copier = BeanCopier.create(sourceList.get(0).getClass(), targetClass, false);
            List<T> resultList = new ArrayList(sourceList.size());
            Iterator var4 = sourceList.iterator();

            while(var4.hasNext()) {
                Object source = var4.next();

                try {
                    T target = targetClass.newInstance();
                    copier.copy(source, target, (Converter)null);
                    resultList.add(target);
                } catch (Exception var7) {

                }
            }

            return resultList;
        } else {
            return Collections.emptyList();
        }
    }
}
