import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @author 黄俭豪
 * @date 2020/9/29 9:25
 */
// 代码自动生成器
public class KuangCode {
    public static void main(String[] args) {
// 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
// 配置策略
// 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //设置代码生成路径
        gc.setOutputDir(projectPath + "/mall-order/src/main/java");
        gc.setAuthor("黄俭豪");
        //  是否生成完打开文件夹
        gc.setOpen(false);
        //是否覆盖原来生成
        gc.setFileOverride(true); // 是否覆盖
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
//2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://rm-wz9qo2um0eygb0a6qso.mysql.rds.aliyuncs.com:3306/shop?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("zhengshukai");
        dsc.setPassword("ZHENGshukai");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
//3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("order");
        //父包名
        pc.setParent("order");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
//4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("commodity","com_sort","item_param","inti_img"); // 设置要映射的表名
        //       驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //        列也支持下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setRestControllerStyle(true);

//        逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
//        自动填充
        TableFill tableFill = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill1 = new TableFill("updata_time", FieldFill.UPDATE);
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
