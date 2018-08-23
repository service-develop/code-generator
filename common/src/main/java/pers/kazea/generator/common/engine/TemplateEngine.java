package pers.kazea.generator.common.engine;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import pers.kazea.generator.common.config.TemplateConfig;
import pers.kazea.generator.common.constant.ConstVal;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 模板引擎抽象类
 * </p>
 *
 * @author hubin
 * @since 2018-01-10
 */
@Slf4j
public abstract class TemplateEngine {

    /**
     * 配置信息
     */
    private TemplateConfig config;


    /**
     * <p>
     * 模板引擎初始化
     * </p>
     */
    public TemplateEngine init(TemplateConfig configBuilder) {
        this.config = configBuilder;
        return this;
    }

    /**
     * <p>
     * 模板真实文件路径
     * </p>
     *
     * @param filePath 文件路径
     * @return
     */
    public abstract String templateFilePath(String filePath);


    /**
     * <p>
     * 输出 java xml 文件
     * </p>
     */
    public TemplateEngine batchOutput() {

        List<Map<String, Object>> list = new ArrayList<>();

        list.forEach(map ->
            config.getPaths().forEach((key, path) -> {
                String name = map.get("name").toString();
                if ( null != path && null != name) {
                    String outPath = String.format((path + File.separator + "%s" + suffix()), name);
                    if (isCreate(outPath)) {
                        try {
                            writer(map, templateFilePath(config.getTemplatePath().get(key)), outPath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            })
        );

        return this;
    }


    /**
     * <p>
     * 将模板转化成为文件
     * </p>
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    public abstract void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception;

    /**
     * <p>
     * 处理输出目录
     * </p>
     */
    public TemplateEngine mkdirs() {
        config.getPaths().forEach((key, value) -> {
            File dir = new File(value);
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if (result) {
//                    log.debug("创建目录： [" + value + "]");
                }
            }
        });
        return this;
    }


    /**
     * <p>
     * 打开输出目录
     * </p>
     */
    public void open() {

        if(!config.isOpen()) return;

        String outDir = config.getOutputDir();
        if (config.isOpen() && StringUtils.isNotEmpty(outDir)) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    } else {
//                        log.debug("文件输出目录:" + outDir);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    protected boolean isCreate(String filePath) {
        // 全局判断【默认】
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            mkDir(file.getParentFile());
        }
        return !exist || config.isFileOverride();
    }

    protected void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }

    /**
     * 文件后缀
     */
    protected String suffix() {
        return ConstVal.JAVA_SUFFIX;
    }


}
