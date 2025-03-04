package com.jcen.springbootinit.generate;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 * 代码生成器
 *
 * @author <a href="https://github.com/Gliangquan">小梁</a>
 */
public class CodeGenerator {

    // TODO 1. 数据库连接配置
    private static final String DB_URL = "jdbc:mysql://localhost:3306/springboot_init_template";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "liangquan0302";

    // 生成路径配置
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String TEMPLATE_DIR = PROJECT_PATH + File.separator + "src/main/resources/templates";

    /**
     * 将数据库字段类型转换为 jdbcType
     */
    private static String toJdbcType(String columnType) {
        if (columnType == null) {
            return "VARCHAR"; // 默认类型
        }
        // 统一转换为大写，方便比较
        columnType = columnType.toUpperCase();
        switch (columnType) {
            case "TEXT":
                return "VARCHAR";
            case "INT":
            case "INTEGER":
                return "BIGINT";
            case "DATETIME":
                return "TIMESTAMP";
            case "TINYINT":
                return "BOOLEAN";
            case "DOUBLE":
            case "FLOAT":
                return "DECIMAL";
            default:
                return columnType; // 其他类型保持原样
        }
    }

    /**
     * 将下划线命名转换为驼峰命名（如 user_name -> userName）
     */
    private static String toCamelCase(String columnName) {
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        for (int i = 0; i < columnName.length(); i++) {
            char ch = columnName.charAt(i);
            if (ch == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(ch));
                    nextUpper = false;
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    /**
     * 获取表的字段信息
     */
    public static List<Map<String, String>> getTableColumns(String tableName) throws Exception {
        List<Map<String, String>> columns = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            DatabaseMetaData metaData = connection.getMetaData();

            // 获取所有字段信息
            try (ResultSet columnsResultSet = metaData.getColumns(null, null, tableName, null)) {
                while (columnsResultSet.next()) {
                    Map<String, String> column = new HashMap<>();
                    String columnName = columnsResultSet.getString("COLUMN_NAME"); // 数据库字段名
                    String columnType = columnsResultSet.getString("TYPE_NAME"); // 数据库字段类型
                    column.put("columnName", columnName); // 数据库字段名称
                    column.put("fieldName", toCamelCase(columnName)); // 实体类字段名称（驼峰命名）
                    column.put("columnType", columnType); // 数据库字段类型
                    column.put("jdbcType", toJdbcType(columnType)); // 映射后的 jdbcType
                    column.put("columnSize", columnsResultSet.getString("COLUMN_SIZE")); // 数据库字段长度
                    column.put("columnComment", columnsResultSet.getString("REMARKS")); // 数据库字段注释
                    column.put("isPrimaryKey", "false"); // 默认设置为非主键
                    columns.add(column);
                }
            }

            // 获取主键信息
            try (ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName)) {
                while (primaryKeysResultSet.next()) {
                    String primaryKeyColumnName = primaryKeysResultSet.getString("COLUMN_NAME");
                    // 标记主键
                    for (Map<String, String> column : columns) {
                        if (primaryKeyColumnName.equals(column.get("columnName"))) {
                            column.put("isPrimaryKey", "true");
                            break;
                        }
                    }
                }
            }
        }
        return columns;
    }

    /**
     * 生成文件
     *
     * @param inputPath  模板文件输入路径
     * @param outputPath 输出路径
     * @param model      数据模型
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 文件不存在则创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }

        // 生成
        try (Writer out = new FileWriter(outputPath)) {
            template.process(model, out);
        }
    }

    /**
     * 生成代码
     *
     * @param packageName   包路径
     * @param dataName      中文名称
     * @param dataKey       文件夹名称
     * @param upperDataKey  实体类名称
     * @param tableName     表名
     */
    public static void generateCode(String packageName, String dataName, String dataKey, String upperDataKey, String tableName) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("packageName", packageName);
        dataModel.put("dataName", dataName);
        dataModel.put("dataKey", dataKey);
        dataModel.put("upperDataKey", upperDataKey);
        dataModel.put("author", "liangquan0302");
        dataModel.put("createDate", new Date());

        try {
            List<Map<String, String>> columns = getTableColumns(tableName);
            dataModel.put("columns", columns);
            dataModel.put("tableName", tableName);

            // 生成 Controller
            generateFile("TemplateController.java.ftl", String.format("%s/generator/controller/%sController.java", PROJECT_PATH, upperDataKey), dataModel);

            // 生成 Service 接口和实现类
            generateFile("TemplateService.java.ftl", String.format("%s/generator/service/%sService.java", PROJECT_PATH, upperDataKey), dataModel);
            generateFile("TemplateServiceImpl.java.ftl", String.format("%s/generator/service/impl/%sServiceImpl.java", PROJECT_PATH, upperDataKey), dataModel);

            // 生成 Entity
            generateFile("TemplateEntity.java.ftl", String.format("%s/generator/model/entity/%s.java", PROJECT_PATH, upperDataKey), dataModel);

            // 生成 VO
            generateFile("TemplateVO.java.ftl", String.format("%s/generator/model/vo/%sVO.java", PROJECT_PATH, upperDataKey), dataModel);

            // 生成 DTO
            generateFile("TemplateAddRequest.java.ftl", String.format("%s/generator/model/dto/%s/%sAddRequest.java", PROJECT_PATH, dataKey, upperDataKey), dataModel);
            generateFile("TemplateQueryRequest.java.ftl", String.format("%s/generator/model/dto/%s/%sQueryRequest.java", PROJECT_PATH, dataKey, upperDataKey), dataModel);
            generateFile("TemplateEditRequest.java.ftl", String.format("%s/generator/model/dto/%s/%sEditRequest.java", PROJECT_PATH, dataKey, upperDataKey), dataModel);
            generateFile("TemplateUpdateRequest.java.ftl", String.format("%s/generator/model/dto/%s/%sUpdateRequest.java", PROJECT_PATH, dataKey, upperDataKey), dataModel);

            // 生成 Mapper 接口
            generateFile("TemplateMapper.java.ftl", String.format("%s/generator/mapper/%sMapper.java", PROJECT_PATH, upperDataKey), dataModel);

            // 生成 Mapper XML 文件
            generateFile("TemplateMapper.xml.ftl", String.format("%s/generator/mapper/%sMapper.xml", PROJECT_PATH, upperDataKey), dataModel);

        } catch (Exception e) {
            System.err.println("生成代码失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 生成单个文件
     *
     * @param templateName 模板名称
     * @param outputPath   输出路径
     * @param dataModel    数据模型
     */
    private static void generateFile(String templateName, String outputPath, Map<String, Object> dataModel) {
        try {
            doGenerate(TEMPLATE_DIR + File.separator + templateName, outputPath, dataModel);
            System.out.println("生成文件成功，路径：" + outputPath);
        } catch (Exception e) {
            System.err.println("生成文件失败：" + outputPath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO 2. 指定生成参数
        String packageName = "com.jcen.springbootinit";  // 包路径
        String dataName = "用户评论";  // 中文名称
        String dataKey = "post";  // 文件夹名称
        String upperDataKey = "Post";  // 实体类名称
        String tableName = "post";  // 表名

        // 生成代码
        generateCode(packageName, dataName, dataKey, upperDataKey, tableName);
    }
}