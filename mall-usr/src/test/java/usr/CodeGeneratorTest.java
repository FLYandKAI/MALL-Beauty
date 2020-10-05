package usr;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class CodeGeneratorTest {
    @Test
    void GeneratiorTest() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
// 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获得当前的项目路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("郑树凯");
        //  是否生成完打开文件夹
        gc.setOpen(false);
        //是否覆盖原来生成
        gc.setFileOverride(true);
        gc.setSwagger2(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);
// 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://rm-wz9qo2um0eygb0a6qso.mysql.rds.aliyuncs.com:3306/shop?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8 ");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("zhengshukai");
        dsc.setPassword("ZHENGshukai");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
// 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity("entites");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        pc.setParent("usr");
        mpg.setPackageInfo(pc);
// 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        映射哪张表  可以多张表！！
        strategy.setInclude("user");
//       驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
//        列也支持下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//自动生成lombok
        strategy.setRestControllerStyle(true);
        
//        逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
//        自动填充
        TableFill tableFill = new TableFill("create_time", FieldFill.INSERT);
<<<<<<< HEAD
        TableFill tableFill1 = new TableFill("updata_time", FieldFill.UPDATE);
=======
        TableFill tableFill1 = new TableFill("update_time", FieldFill.UPDATE);
>>>>>>> hao
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(tableFill);
        tableFills.add(tableFill1);
        strategy.setTableFillList(tableFills);
//       乐观锁
        strategy.setVersionFieldName("last_version");

        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符

        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
