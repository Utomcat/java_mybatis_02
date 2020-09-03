package com.ranyk.mybatis_ch02.annotation.annotation02;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * ClassName:NameScannerProcessor
 * Description: 编译时注解处理器
 *
 * @author ranyi
 * @date 2020-08-06 15:52
 * Version: V1.0
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameScannerProcessor extends AbstractProcessor {

    @Override
    public void init(ProcessingEnvironment processingEnv){
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if(!roundEnv.processingOver()){
            for(Element element : roundEnv.getElementsAnnotatedWith(NameScanner.class)){
                String name = element.getSimpleName().toString();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "element name: " + name);
            }
        }

        return false;
    }
}
