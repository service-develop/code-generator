package pers.kazea.generator.common.config;

import pers.kazea.generator.common.constant.FileType;

import java.util.List;
import java.util.Map;

public interface TemplateConfig {

    List<Map<String, Object>> getObjectMaps();

    default boolean isFileOverride(){
        return true;
    }

    default boolean isOpen(){
        return false;
    }

    String getOutputDir();

    Map<String, String> getPaths();

    Map<FileType, String> getTemplatePath();

}
