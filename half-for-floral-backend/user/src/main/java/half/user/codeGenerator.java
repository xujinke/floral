package half.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.Types;
import java.util.Collections;

/**
 * @author 33287
 * @ClassName codeGenerator
 * @date 2024年06月04日
 * @version: 1.0
 */
public class codeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/floral_user", "floral", "040626")
                .globalConfig(builder -> {
                    builder.author("half") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("F:\\桌面\\半缘花意\\coding\\half-for-floral-backend\\user\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("half.user") // 设置父包名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "F:\\桌面\\半缘花意\\coding\\half-for-floral-backend\\user\\src\\main\\resources\\mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder
                                .entityBuilder()
                                .enableLombok()
                                .enableFileOverride()
                                .logicDeleteColumnName("deleted")
                                .addTableFills(new Column("create_time", FieldFill.INSERT))
                                .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                                .controllerBuilder()
                                .enableFileOverride()
                                .enableRestStyle()
                                .enableHyphenStyle()
                                .serviceBuilder()
                                .formatServiceFileName("%sService")
                                .enableFileOverride()
                                .mapperBuilder()
                                .enableFileOverride()
                                .build()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
