package pers.kazea.code.mysql.config;


import pers.kazea.code.mysql.config.builder.ConfigBuilder;
import pers.kazea.generator.common.constant.FileType;

/**
 * <p>
 * 文件覆盖接口
 * </p>
 *
 * @author hubin
 * @since 2018-08-07
 */
public interface IFileCreate {

    /**
     * <p>
     * 自定义判断是否创建文件
     * </p>
     *
     * @param configBuilder 配置构建器
     * @param fileType      文件类型
     * @param filePath      文件路径
     * @return
     */
    boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath);

}
