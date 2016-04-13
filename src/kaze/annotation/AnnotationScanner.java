package kaze.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class AnnotationScanner {
    public static void main(String[] args) {
        /*ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(<DO_YOU_WANT_TO_USE_DEFALT_FILTER>);

        scanner.addIncludeFilter(new AnnotationTypeFilter(<TYPE_YOUR_ANNOTATION_HERE>.class));

        for (BeanDefinition bd : scanner.findCandidateComponents(<TYPE_YOUR_BASE_PACKAGE_HERE>))
        System.out.println(bd.getBeanClassName());*/
    }
}
