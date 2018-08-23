package pers.kazea.code.mysql.config;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.kazea.code.mysql.config.po.TableInfo;

/**
 * <p>
 * 输出文件配置
 * </p>
 *
 * @author hubin
 * @since 2017-01-18
 */
@Data
@Accessors(chain = true)
public abstract class FileOutConfig {

    /**
     * 模板
     */
    private String templatePath;

    public FileOutConfig() {
        // to do nothing
    }

    public FileOutConfig(String templatePath) {
        this.templatePath = templatePath;
    }

    /**
     * 输出文件
     */
    public abstract String outputFile(TableInfo tableInfo);
}
