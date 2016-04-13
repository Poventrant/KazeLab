package kaze.annotation;

import java.lang.annotation.Annotation;

@ClassAnnotation("annotationTest")
public class AnnotationTest {

    @MethodAnnotation("test")
    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        if(clazz.isAnnotationPresent(ClassAnnotation.class)) {
            Annotation annotation = clazz.getAnnotation(ClassAnnotation.class);
            ClassAnnotation classAnnotation = (ClassAnnotation) annotation;
            System.out.printf("%nPriority :%s", classAnnotation.value());

        }
    }
}
