package pers.kazea.code.mysql.config;


import pers.kazea.code.mysql.config.rules.DbColumnType;

/**
 * <p>
 * 数据库字段类型转换
 * </p>
 *
 * @author hubin
 * @since 2017-01-20
 */
public interface ITypeConvert {


    /**
     * <p>
     * 执行类型转换
     * </p>
     *
     * @param globalConfig 全局配置
     * @param fieldType    字段类型
     * @return
     */
    DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType);

}
